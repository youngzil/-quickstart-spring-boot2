http://blog.csdn.net/tengxing007/article/details/72675168
http://blog.csdn.net/zknxx/article/details/53428669

Spring Boot 实现热部署很简单
使用 Spring Loaded
使用 spring-boot-devtools


pringloaded
在 POM 文件中添加依赖
<build>
 <plugins>
 <plugin>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-maven-plugin</artifactId>
  <dependencies>
   <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>springloaded</artifactId>
    <version>1.2.6.RELEASE</version>
   </dependency>
  </dependencies>
 </plugin>
 </plugins>
</build>
添加完毕，启动项目


spring-boot-devtools
添加 POM 文件依赖
<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-devtools</artifactId>
 <optional>true</optional> <!-- 这个需要为 true 热部署才有效 -->
 <!-- optional=true,依赖不会传递，该项目依赖devtools；之后依赖该项目的项目如果想要使用devtools，需要重新引入 -->
</dependency>
然后还是使用 mvn spring-boot:run 启动项目，随意更改代码即可看到效果。
如果我们想指定让 devtools 监听指定文件夹，那么可以在 application.yml或者application.properties中添 配置
spring.devtools.restart.additional-paths=your path，注意这里需要改成 yml 文件的格式。
页面热部署
devtools可以实现页面热部署，即页面修改后会立即生效。你可以在application.properties文件中配置spring.thymeleaf.cache=false来实现这个功能。


spring:
    devtools:
        restart:
            #热部署生效
          enabled: true
            #设置重启的目录
            #additional-paths: src/main/java
            #classpath目录下的WEB-INF文件夹内容修改不重启
          exclude: WEB-INF/**












