1、启动和停止WildFly
参考
/Users/yangzl/git/quickstart-application-container/docs/WildFly介绍.md


2、执行mvn clean install
在pom.xml中有WildFly的部署地址和用户名、密码
执行成果后就部署到WildFly容器中了


3、进入http://localhost:9990/console/index.html
查看Deployments选项--->start--->Deployment (1)


4、访问
http://127.0.0.1:8080/quickstart-spring-app-wildfly-0.0.1-SNAPSHOT/




参考
https://medium.com/swlh/how-to-deploy-spring-boot-application-in-wildfly-application-server-b3670c031ad4
https://github.com/musibs/medium-articles/tree/master/spring-boot-wildfly-demo


