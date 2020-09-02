启动WebfluxNettyApplication后访问http://localhost:8090/api/get


curl http://localhost:8090/date
curl http://localhost:8090/time
curl http://localhost:8090/times



通过Spring Boot，我们可以在应用程序属性文件中为Tomcat，Jetty和Undertow配置访问日志。但是，Netty尚无此支持。
要启用Netty访问日志记录，我们应该 在运行应用程序时设置-Dreactor.netty.http.server.accessLogEnabled = true：
mvn spring-boot:run -Dreactor.netty.http.server.accessLogEnabled=true


参考
https://www.baeldung.com/spring-boot-reactor-netty
https://stackabuse.com/reactive-programming-with-spring-5-webflux/



