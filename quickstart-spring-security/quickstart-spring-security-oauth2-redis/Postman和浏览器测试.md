postman查看oauth2-memory.postman_collection.json

一、授权码模式

1、浏览器输入
http://localhost:9001/oauth2-server/oauth/authorize?response_type=code&client_id=resourceServer1&state=xyz&redirect_uri=http://localhost:9002/oauth2-client/redirect

2、使用postman获取token：看
{
    "access_token": "4fa59111-1190-426d-bf92-885b8360ab6e",
    "token_type": "bearer",
    "refresh_token": "8fc06faf-90d8-4200-8f00-cdb4433cee6a",
    "expires_in": 7199,
    "scope": "select read write"
}

3、使用token访问资源
http://localhost:9003/oauth2-resource/user?access_token=4fa59111-1190-426d-bf92-885b8360ab6e

check Toekn
http://localhost:9001/oauth2-server/oauth/check_token?token=4fa59111-1190-426d-bf92-885b8360ab6e


4、使用postman刷新token
{
    "access_token": "e50b68b2-6a68-4ad4-99b6-d892a4ffb2a7",
    "token_type": "bearer",
    "refresh_token": "8fc06faf-90d8-4200-8f00-cdb4433cee6a",
    "expires_in": 7199,
    "scope": "select read write"
}

5、使用token访问资源
http://localhost:9003/oauth2-resource/user?access_token=e50b68b2-6a68-4ad4-99b6-d892a4ffb2a7

check Toekn
http://localhost:9001/oauth2-server/oauth/check_token?token=e50b68b2-6a68-4ad4-99b6-d892a4ffb2a7


二、客户端模式

1、使用postman获取token
{
    "access_token": "5f500187-241f-45d7-8318-b0146a4c73ed",
    "token_type": "bearer",
    "expires_in": 7199,
    "scope": "select read write"
}

2、使用token访问资源
http://localhost:9003/oauth2-resource/user?access_token=5f500187-241f-45d7-8318-b0146a4c73ed

check Toekn
http://localhost:9001/oauth2-server/oauth/check_token?token=5f500187-241f-45d7-8318-b0146a4c73ed



三、密码模式
1、使用postman获取token
{
    "access_token": "e50b68b2-6a68-4ad4-99b6-d892a4ffb2a7",
    "token_type": "bearer",
    "refresh_token": "8fc06faf-90d8-4200-8f00-cdb4433cee6a",
    "expires_in": 6973,
    "scope": "select read write"
}


2、使用token访问资源
http://localhost:9003/oauth2-resource/user?access_token=e50b68b2-6a68-4ad4-99b6-d892a4ffb2a7

check Toekn
http://localhost:9001/oauth2-server/oauth/check_token?token=e50b68b2-6a68-4ad4-99b6-d892a4ffb2a7



四、简化模式
http://localhost:9001/oauth2-server/oauth/authorize?response_type=token&client_id=resourceServer1&redirect_uri=http://localhost:9002/oauth2-client/redirect

回调时候连接里面有Token
http://localhost:9002/oauth2-client/redirect#access_token=e50b68b2-6a68-4ad4-99b6-d892a4ffb2a7&token_type=bearer&expires_in=6883&scope=select%20read%20write

2、使用token访问资源
http://localhost:9003/oauth2-resource/user?access_token=e50b68b2-6a68-4ad4-99b6-d892a4ffb2a7

check Toekn
http://localhost:9001/oauth2-server/oauth/check_token?token=e50b68b2-6a68-4ad4-99b6-d892a4ffb2a7

