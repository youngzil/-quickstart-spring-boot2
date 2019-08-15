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




Oauth2讲解参考
https://coolshell.cn/articles/19395.html
https://segmentfault.com/a/1190000012332319
https://segmentfault.com/a/1190000012332319
http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html


文章参考
https://blog.csdn.net/u013815546/article/category/7090661
https://blog.csdn.net/qq_30905661/article/category/8082091
https://juejin.im/post/5c997ce5e51d4507853e6a9f
https://blog.csdn.net/bluuusea/article/details/80284458
https://blog.csdn.net/LightOfMiracle/article/details/79151074
https://blog.csdn.net/baidu_37307156/article/details/84995194



使用内存方式，流程学习参考，Springboot2实现OAuth2.0四种授权方式
https://blog.csdn.net/AaronSimon/article/details/83546827
https://blog.csdn.net/AaronSimon/article/details/84326629


授权码code方式代码参考
https://github.com/fuyuanwu/spring-security5


Springboot2实现OAuth2.0四种授权方式
https://github.com/nivance/spring-security-oauth2


代码参考
https://www.jianshu.com/p/734348fb6cbb
https://github.com/lexburner/oauth2-demo

