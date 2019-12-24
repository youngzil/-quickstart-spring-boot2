问题：http转发https时候，转发只能是Get方式，post不行，而且丢失post请求的body
或者只能使用Nginx来做http redirect to https


1、生成证书

2、配置文件
```aidl
server:
  ssl:
    key-store: classpath:keystore.p12
    key-store-password: 123456
    key-store-type: PKCS12
    key-alias: oauth2-jetty
    key-password: 123456
  port: 9001
  servlet:
    context-path: /oauth2
#    http2:
#      enabled: true

```

3、POM依赖
```
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
<!--            <exclusions>-->
<!--                <exclusion>-->
<!--                    <groupId>org.springframework.boot</groupId>-->
<!--                    <artifactId>spring-boot-starter-tomcat</artifactId>-->
<!--                </exclusion>-->
<!--            </exclusions>-->
        </dependency>

        <!-- Jetty适合长连接应用，就是聊天类的长连接 -->
        <!-- 使用Jetty，需要在spring-boot-starter-web排除spring-boot-starter-tomcat，因为SpringBoot默认使用tomcat -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jetty</artifactId>
        </dependency>

        <!--        HTTP2依赖-->
        <!--<dependency>
            <groupId>org.eclipse.jetty</groupId>
            <artifactId>jetty-alpn-conscrypt-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.eclipse.jetty.http2</groupId>
            <artifactId>http2-server</artifactId>
        </dependency>-->
```

4、类     
tomcat参考ServerConfig.java       
jetty参考     


HTTP2开启参考       
SpringBoot2.2 官方指导手册中文版3--嵌入式Web服务器     
http://www.1024sky.cn/blog/article/6        



如何在Spring Boot Java应用程序中启用HTTPS     

tomcat参考        
https://www.thomasvitale.com/https-spring-boot-ssl-certificate/     
https://www.javadevjournal.com/spring-boot/how-to-enable-http-https-in-spring-boot/     

Jetty参考     
https://stackoverflow.com/questions/49283130/spring-boot-jetty-auto-redirect-http-port-80-requests-to-https-port-8443       




