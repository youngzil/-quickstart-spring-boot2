现在先启动工程，打开浏览器访问：  
http://localhost:8080/actuator 
http://localhost:8888/actuator 

登录  
admin/admin



自定义端点 FeaturesEndpoint.java
http://localhost:8080/actuator/features

扩展端点:InfoWebEndpointExtension.java



3. 接口详解 http://localhost:8080/actuator/XXX  
3.1 /health  
3.2 /info  
3.3 /beans  
3.4 /conditions  
3.5 /shutdown  
3.6 /mappings  
3.7 /threaddump






/auditevents：同Actuator 1.x，还可以通过关键字进行过滤
/beans：同Actuator 1.x，不可以过滤
/conditions：返回服务中的自动配置项
/configprops：允许我们获取@ConfigurationProperties的bean对象
/env：返回当前的环境变量，我们也可以检索某个值
/flyway：提供Flyway数据库迁移的详细情况
/health：同Actuator 1.x
/heapdump：返回应用服务使用地jvm堆dump信息
/info：同Actuator 1.x
/liquibase：类似于 /flyway，但是组件工具为Liquibase
/logfile：返回应用的普通日志文件
/loggers：允许我们查询和修改应用的日志等级
/metrics：同Actuator 1.x
/prometheus：返回与/metrics类似，与Prometheus server一起使用
/scheduledtasks：返回应用的周期性任务
/sessions：同Actuator 1.x
/shutdown：同Actuator 1.x
/threaddump：dump所依赖的jvm线程信息



参考  
https://juejin.im/post/6844903959237246989  
https://juejin.im/post/6844903716978425870  
https://segmentfault.com/a/1190000021611510  
https://github.com/Richard-yyf/springboot-actuator-prometheus-test  
