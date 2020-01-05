把spring-boot项目按照平常的web项目一样发布到tomcat容器下

一、修改打包形式
在pom.xml里设置 <packaging>war</packaging>

二、移除嵌入式tomcat插件
在pom.xml里找到spring-boot-starter-web依赖节点，在其中添加如下代码，
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <!-- 移除嵌入式tomcat插件 -->
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
或者
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <scope>provided</scope>
</dependency>
注意：第二种依然可以使用内嵌的tomcat来调试：spring-boot更加强大的一点就是：即便项目是以上配置，依然可以用内嵌的tomcat来调试，启动命令和以前没变，还是：mvn spring-boot:run。
3、因为要在本地启动，所有添加Tomcatjar包，将scope设置为provided，这样在打war包的时候不会包含这个jar包
三、添加servlet-api的依赖
下面两种方式都可以，任选其一
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope>
</dependency>
或者
<dependency>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>tomcat-servlet-api</artifactId>
    <version>8.0.36</version>
    <scope>provided</scope>
</dependency>
四、修改启动类，并重写初始化方法
我们平常用main方法启动的方式，都有一个App的启动类，代码如下：

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
我们需要类似于web.xml的配置方式来启动spring上下文了，在Application类的同级添加一个SpringBootStartApplication类，其代码如下:

/**
 * 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {
 
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(Application.class);
    }
}

或者合并起来

@EnableAutoConfiguration
@SpringBootApplication
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(App.class);
    }

}

五、打包部署
在项目根目录下（即包含pom.xml的目录），在命令行里输入： 
mvn clean package即可， 等待打包完成，出现[INFO] BUILD SUCCESS即为打包成功。 
然后把target目录下的war包放到tomcat的webapps目录下，启动tomcat，即可自动解压部署。 
最后在浏览器中输入

http://localhost:[端口号]/[打包项目名]/

发布成功
--------------------- 
作者：Paranoia_ZK 
来源：CSDN 
原文：https://blog.csdn.net/paranoia_zk/article/details/78128792 
版权声明：本文为博主原创文章，转载请附上博文链接！