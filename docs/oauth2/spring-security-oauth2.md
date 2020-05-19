Oauth2讲解
密码模式（resource owner password credentials）(为遗留系统设计)(支持refresh token)
授权码模式（authorization code）(正宗方式)(支持refresh token)
简化模式（implicit）(为web浏览器应用设计)(不支持refresh token)
客户端模式（client credentials）(为后台api服务消费者设计)(不支持refresh token)



spring-security-oauth2封装了四种模式，支持哪种模式，主要看client信息里的授权方式配置


token存储方式：5种
client存储方式：jdbc和内存
User存储方式：实现类，自己实现
Client调用方式：代码（使用客户端jar，使用http方式）、postman或浏览器
登录页面和授权页面、错误页面、登出页面等，可以定制或者使用默认
刷新token
校验token


---------------------------------------------------------------------------------------------------------------------

@EnableResourceServer会给Spring Security的FilterChan添加一个OAuth2AuthenticationProcessingFilter
OAuth2AuthenticationProcessingFilter会使用OAuth2AuthenticationManager来验证token。 
OAuth2AuthenticationManager#authenticate(Authentication authentication)


TokenEndpoint.java中的/oauth/token
CheckTokenEndpoint.java中的/oauth/check_token


TokenEndpoint.java中的/oauth/token内：
1、获取ClientDetails
ClientDetails authenticatedClient = getClientDetailsService().loadClientByClientId(clientId);

InMemoryClientDetailsService
JdbcClientDetailsService
RedisClientDetailsService【自己重写】


2、通过OAuth2RequestFactory构建TokenRequest
TokenRequest tokenRequest = getOAuth2RequestFactory().createTokenRequest(parameters, authenticatedClient);


3、通过TokenGranter的grant方法获取OAuth2AccessToken
OAuth2AccessToken token = getTokenGranter().grant(tokenRequest.getGrantType(), tokenRequest);

AuthorizationCodeTokenGranter
ClientCredentialsTokenGranter
ImplicitTokenGranter
RefreshTokenGranter
ResourceOwnerPasswordTokenGranter


4、用户信息UserDetailsService和用户管理UserDetailsManager接口实现类：
InMemoryUserDetailsManager
JdbcUserDetailsManager
RedisUserDetailsManager【自己重写】


5、TokenGranter中：调用TokenStore
TokenServices（AuthorizationServerTokenServices）中方法：实现类DefaultTokenServices.java
OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException;
OAuth2AccessToken refreshAccessToken(String refreshToken, TokenRequest tokenRequest) throws AuthenticationException;
OAuth2AccessToken getAccessToken(OAuth2Authentication authentication);


6、TokenStore接口的实现：
InMemoryTokenStore
JdbcTokenStore
JwkTokenStore
JwtTokenStore
RedisTokenStore


Redis存储的Key：
private static final String ACCESS = "access:";
private static final String AUTH_TO_ACCESS = "auth_to_access:";
private static final String AUTH = "auth:";
private static final String REFRESH_AUTH = "refresh_auth:";
private static final String ACCESS_TO_REFRESH = "access_to_refresh:";
private static final String REFRESH = "refresh:";
private static final String REFRESH_TO_ACCESS = "refresh_to_access:";
private static final String CLIENT_ID_TO_ACCESS = "client_id_to_access:";
private static final String UNAME_TO_ACCESS = "uname_to_access:";



生成Token的时候：
ACCESS+token = OAuth2AccessToken对象，全部的Token信息
AUTH+token = OAuth2Authentication对象
AUTH_TO_ACCESS+MD5值（USERNAME+CLIENT_ID+SCOPE） = OAuth2AccessToken对象，全部的Token信息
UNAME_TO_ACCESS+clientId+:+uname = OAuth2AccessToken对象，全部的Token信息
CLIENT_ID_TO_ACCESS+clientId = OAuth2AccessToken对象，全部的Token信息

REFRESH_TO_ACCESS+refreshToken = token值
ACCESS_TO_REFRESH+token = refreshToken值


生成RefreshToken的时候：
REFRESH+refreshToken = OAuth2RefreshToken对象，refreshToken值
REFRESH_AUTH+refreshToken = OAuth2Authentication对象



请求Token，是否正常返回，
根据返回判断此次请求resourceId是否在授权范围内
根据返回的ClientId查询客户端信息是否包含Token返回的scope


网关逻辑：
请求Token，是否正常返回
根据返回信息校验client_id是否和请求传过来的一致
返回的token信息的exp是否过期

{"aud":["client100"],"exp":1565881177,"client_id":"resourceServer1","scope":["select","read"]}



在已经请求了Token的时候，修改授权码模式不生效，只有等Token失效了再修改才生效
强制生效：
删除oauth2:auth_to_access:XXX这个Key即可


客户端模式保存的Key：
oauth2:access:e32b40d8-3793-4f82-aa9f-8f5bf2c87b07
oauth2:auth:e32b40d8-3793-4f82-aa9f-8f5bf2c87b07
oauth2:auth_to_access:83a6b9ced54dafc3bd439ed36941c0f8  删除即可
oauth2:client_id_to_access:app_1587020196405


curl --location --request POST 'http://127.0.0.1:9001/oauth2/oauth/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'client_id=app_1587020196405' \
--data-urlencode 'client_secret=e4cccdb8ff2621a722cc8b8b164fa95c'

{
    "access_token": "27ea92d3-2fd6-4037-b4e7-90d9bcca1b41",
    "token_type": "bearer",
    "refresh_token": "5b4d0dc5-cb6b-4338-b9df-9a3b700a5273",
    "expires_in": 7096,
    "scope": "all"
}

客户端和刷新Token保存的Key：
前面3个是access_token
后面4个是refresh_token

oauth2:access:27ea92d3-2fd6-4037-b4e7-90d9bcca1b41
oauth2:access_to_refresh:27ea92d3-2fd6-4037-b4e7-90d9bcca1b41
oauth2:auth:27ea92d3-2fd6-4037-b4e7-90d9bcca1b41
oauth2:auth_to_access:83a6b9ced54dafc3bd439ed36941c0f8

oauth2:client_id_to_access:app_1587020196405
oauth2:refresh:5b4d0dc5-cb6b-4338-b9df-9a3b700a5273
oauth2:refresh_auth:5b4d0dc5-cb6b-4338-b9df-9a3b700a5273
oauth2:refresh_to_access:5b4d0dc5-cb6b-4338-b9df-9a3b700a5273


大致分为四类：
1、auth权限、client_id信息
oauth2:auth:27ea92d3-2fd6-4037-b4e7-90d9bcca1b41
oauth2:client_id_to_access:app_1587020196405

2、access、access和auth关联
oauth2:access:27ea92d3-2fd6-4037-b4e7-90d9bcca1b41
oauth2:auth_to_access:83a6b9ced54dafc3bd439ed36941c0f8

3、refresh、refresh和auth关联
oauth2:refresh:5b4d0dc5-cb6b-4338-b9df-9a3b700a5273
oauth2:refresh_auth:5b4d0dc5-cb6b-4338-b9df-9a3b700a5273

4、access和refresh关联、refresh和access关联
oauth2:refresh_to_access:5b4d0dc5-cb6b-4338-b9df-9a3b700a5273
oauth2:access_to_refresh:27ea92d3-2fd6-4037-b4e7-90d9bcca1b41


---------------------------------------------------------------------------------------------------------------------

OAuth 2.0
https://oauth.net/
https://oauth.net/code/java/

参考
/Users/yangzl/git/notes/document/doc/base/SSO实现OpenID和OAuth.md


Oauth2讲解参考
https://coolshell.cn/articles/19395.html
https://segmentfault.com/a/1190000012332319
http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html



SpringSecurityOauth2源码解读
https://juejin.im/post/5b3f283af265da0f6012ef30
http://blog.didispace.com/spring-security-oauth2-xjf-3/


文章参考
https://blog.csdn.net/u013815546/article/category/7090661
https://blog.csdn.net/qq_30905661/article/category/8082091
https://blog.csdn.net/wuzhiwei549/article/details/79815491
https://juejin.im/post/5c997ce5e51d4507853e6a9f
https://blog.csdn.net/bluuusea/article/details/80284458
https://blog.csdn.net/LightOfMiracle/article/details/79151074
https://blog.csdn.net/baidu_37307156/article/details/84995194
https://blog.csdn.net/xichenguan/article/details/77886871



WebSecurityConfigurerAdapter配置详解
https://blog.csdn.net/neweastsun/article/details/79321320


使用内存方式，流程学习参考，Springboot2实现OAuth2.0四种授权方式
https://blog.csdn.net/AaronSimon/article/details/83546827
https://blog.csdn.net/AaronSimon/article/details/84326629


授权码code方式代码参考
https://github.com/fuyuanwu/spring-security5


Springboot2实现OAuth2.0四种授权方式
https://github.com/nivance/spring-security-oauth2
https://spring.io/guides/tutorials/spring-boot-oauth2/


代码参考
https://www.jianshu.com/p/734348fb6cbb
https://github.com/lexburner/oauth2-demo
https://github.com/zth390872451/oauth2-redis-mysql

