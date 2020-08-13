Spring WebFlux 除了提供 REST web 服务外，还支持渲染动态 HTML 内容，Spring WebFlux 支持一系列模板引擎，包括 Thymeleaf、FreeMarker 和 Mustache。

Spring WebFlux默认使用Netty


Spring Boot 为以下的模板引擎提供了自动配置的支持：
FreeMarker
Thymeleaf
Mustache
当你使用了其中某个模板引擎，并选择了 Spring Boot 自动配置，你需要将你的模板文件放在 src/main/resources/templates 目录下，以便被 Spring Boot 发现。



参考
https://my.oschina.net/u/1000241/blog/3125451
https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html
https://s0docs0spring0io.icopy.site/spring/docs/current/spring-framework-reference/web-reactive.html#webflux
https://github.com/jittagornp/spring-boot-webflux-example/tree/master/spring-boot-webflux-helloworld
https://github.com/developerworks/demo-spring-boot-webflux-functional
https://github.com/callicoder/spring-webflux-reactive-rest-api-demo
https://blog.csdn.net/j903829182/article/details/80297336



webflux-security示例
https://github.com/duyleduc/spring-boot-webflux-security
https://github.com/jittagornp/spring-boot-webflux-example/tree/master/spring-boot-webflux-security


性能测试 —— Tomcat、Jetty、Undertow 基准测试
http://www.iocoder.cn/Performance-Testing/Tomcat-Jetty-Undertow-benchmark/

https://colobu.com/2015/11/17/Jax-RS-Performance-Comparison/
https://colobu.com/2015/11/15/best-available-java-restful-micro-frameworks/

Jersey + Grizzly
Jersey + Jetty
Dropwizard
RESTEasy + Netty
RESTEasy + Undertow

因为追求轻量级，便于发布到docker容器中，我也不会考察JBOSS, Tomcat这样的JEE容器， 而是选用jetty, undertow这样的嵌入式容器。

