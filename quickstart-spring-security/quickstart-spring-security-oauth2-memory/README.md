一、授权码模式

1、浏览器输入
http://localhost:8080/oauth/authorize?response_type=code&client_id=client1&redirect_uri=http://baidu.com

2、使用postman获取token：看
{
    "access_token": "fc79700b-fc59-4b0c-8449-bfcd9feabd5f",
    "token_type": "bearer",
    "refresh_token": "f24ab771-7b63-4924-8c9f-b1768518b79b",
    "expires_in": 43199,
    "scope": "test"
}

3、使用token访问资源
http://localhost:8088/user?access_token=fc79700b-fc59-4b0c-8449-bfcd9feabd5f

check Toekn
http://localhost:8080/oauth/check_token?token=fc79700b-fc59-4b0c-8449-bfcd9feabd5f


4、使用postman刷新token
{
    "access_token": "f0e4f9c3-8d71-4de1-9998-3ea963a9b221",
    "token_type": "bearer",
    "refresh_token": "f24ab771-7b63-4924-8c9f-b1768518b79b",
    "expires_in": 43199,
    "scope": "test"
}

5、使用token访问资源
http://localhost:8088/user?access_token=f0e4f9c3-8d71-4de1-9998-3ea963a9b221

check Toekn
http://localhost:8080/oauth/check_token?token=f0e4f9c3-8d71-4de1-9998-3ea963a9b221


二、客户端模式

1、使用postman获取token
{
    "access_token": "40540b9b-54c7-4a69-8fb8-873269a8fe88",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "test"
}

2、使用token访问资源
http://localhost:8088/user?access_token=40540b9b-54c7-4a69-8fb8-873269a8fe88

check Toekn
http://localhost:8080/oauth/check_token?token=40540b9b-54c7-4a69-8fb8-873269a8fe88



三、密码模式
1、使用postman获取token

{
    "access_token": "f0e4f9c3-8d71-4de1-9998-3ea963a9b221",
    "token_type": "bearer",
    "refresh_token": "f24ab771-7b63-4924-8c9f-b1768518b79b",
    "expires_in": 42258,
    "scope": "test"
}


2、使用token访问资源
http://localhost:8088/user?access_token=f0e4f9c3-8d71-4de1-9998-3ea963a9b221

check Toekn
http://localhost:8080/oauth/check_token?token=f0e4f9c3-8d71-4de1-9998-3ea963a9b221




四、简化模式
http://localhost:8080/oauth/authorize?response_type=token&client_id=client1&redirect_uri=http://baidu.com

回调时候
https://www.baidu.com/#access_token=f0e4f9c3-8d71-4de1-9998-3ea963a9b221&token_type=bearer&expires_in=42062&scope=test

2、使用token访问资源
http://localhost:8088/user?access_token=f0e4f9c3-8d71-4de1-9998-3ea963a9b221

check Toekn
http://localhost:8080/oauth/check_token?token=f0e4f9c3-8d71-4de1-9998-3ea963a9b221




参考
https://blog.csdn.net/AaronSimon/article/details/83546827
https://blog.csdn.net/AaronSimon/article/details/84326629

