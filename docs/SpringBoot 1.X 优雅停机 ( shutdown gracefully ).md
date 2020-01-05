SpringBoot应用程序的关闭目前总结起来有4种方式：

Rest接口：使用spring-boot-starter-actuator模块里的ShutdownEndpoint
SpringApplication的exit静态方法：直接调用该静态方法即可
JMX：使用SpringBoot内部提供的MXBean
使用第三方进程管理工具


https://blog.csdn.net/qijiqiguai/article/details/78805838
https://blog.csdn.net/Quincuntial/article/details/54410916
http://www.spring4all.com/article/1022
https://fangjian0423.github.io/2017/06/28/springboot-application-exit/
https://blog.csdn.net/chinrui/article/details/78685032

http://blog.didispace.com/books/spring-boot-reference/IV.%20Spring%20Boot%20features/23.9%20Application%20exit.html


