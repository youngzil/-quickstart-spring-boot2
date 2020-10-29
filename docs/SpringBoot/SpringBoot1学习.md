SpringBoot是Spring主推的基于"习惯优于配置（约定优于配置）"的原则，快速搭建应用的框架
Springboot可以进行Java EE开发的绝大多数应用场景：Web开发，数据访问，安全控制，批处理，异步消息，系统集成，开发与部署，应用监控，分布式系统开发等

为什么会有SpringBoot
Spring在JavaEE开发中是实际意义上的标准，但是开发中会遇到下述问题
  大量配置文件的定义
  与第三方软件整个的技术问题

而Spring每个新版本的推出都以减少配置作为自己的主要目标
  （1）注解声明Bean
  （2）Java配置代替xml配置

SpringBoot具有如下特点
  （1）习惯优于配置，大多数配置默认，只需很好配置
  （2）项目快速搭建，无配置整合第三方框架
  （3）自动配置和Java配置
  （4）内嵌Servlet（Tomcat）容器，应用可jar包运行
  （5）运行中应用状态的监控

SpringBoot完全是一个单纯的基于spring的应用。



SpringBoot约定优于配置：不会覆盖，依据先读取到的配置为准

SpringBoot配置文件可以使用yml格式和properties格式
分别的默认命名为：application.yml、application.properties

SpringBoot配置文件默认可以放到以下目录中，可以自动读取到：
项目根目录中config目录下
项目根目录下
项目resources目录中config目录下
项目的resources目录下

读取顺序
 如果在不同的目录中存在多个配置文件，它的读取顺序是：
1、config/application.properties（项目根目录中config目录下）
2、application.properties（项目根目录下）
3、resources/config/application.properties（项目resources目录中config目录下）
4、resources/application.properties（项目的resources目录下）

总结
源码类：ConfigFileApplicationListener
分别从file:./config/, file:./, classpath:/config/, classpath:/ 这四个位置依次读取后缀名为properties, xml, yml, yaml的配置文件
优先级由高到低，对于相同的属性配置，高优先级的配置会覆盖优先级低的配置；对于其他不同的属性配置，则会进行互补。
优先级相同的情况下，同时有application.properties和application.yml，那么application.properties里面的属性就会覆盖application.yml里的属性，因为properties比yml优先加载。

1、如果同一个目录下，有application.yml也有application.properties，默认先读取application.properties。
2、如果同一个配置属性，在多个配置文件都配置了，默认使用第1个读取到的，后面读取的不覆盖前面读取到的。
3、创建SpringBoot项目时，一般的配置文件放置在“项目的resources目录下”


Spring Boot 可以从properties文件、YAML文件、环境变量和命令参数获取配置。默认SpringApplication 将从如下位置加载 application.properties获取 application.yml 文件作为配置。顺序如下:

命令参数 java -jar target.jar --spring.config.location=/path/application.properties
targer.jar所在目录的子目录 config 下的 application.properties 或者 application.yml
target.jar当前目录的application.properties 或者 application.yml
classpath 根目录下的 application.properties 或者 application.yml
@PropertySource 或者 @PropertySources 注解
SpringApplication.setDefaultProperties

SpringBoot外部配置读取顺序
外部配置加载顺序：
https://blog.csdn.net/caychen/article/details/79981446
https://blog.csdn.net/u010963948/article/details/76550223
https://blog.csdn.net/wu2374633583/article/details/80075198
Spring Boot也可以从以下位置加载配置； 优先级从高到低；高优先级的配置覆盖低优先级的配置，所有的配置会形成互补配置。
0. @TestPropertySource 注解 
1、命令行参数
所有的配置都可以在命令行上进行指定
java -jar spring-boot-02-config-SNAPSHOT.jar --server.port=8087 --server.context-path=/abc
多个配置用空格分开； --配置项=值
2、来自java:comp/env的JNDI属性
3、Java系统属性（System.getProperties()）
4、操作系统环境变量
5、RandomValuePropertySource配置的random.*属性值，只有在random.*里包含的属性会产生一个RandomValuePropertySource

由jar包外 ----向----> jar包内进行寻找；
优先加载带profile
6、在打包的jar外的应用程序配置文件（通过--spring.config.location=/path/test_evn.properties）
6、jar包外部的application-{profile}.properties或application.yml(带spring.profile)配置文件
7、jar包内部的application-{profile}.properties或application.yml(带spring.profiles)配置文件，（application.properties，包含YAML和profile变量） 

再来加载不带profile
8、jar包外部的application.properties或application.yml(不带spring.profile)配置文件
9、jar包内部的application.properties或application.yml(不带spring.profile)配置文件
10、@Configuration注解类上的@PropertySource
11、通过SpringApplication.setDefaultProperties指定的默认属性

所以，正对6和7的情况，我们一般在应用程序外部放置配置yml文件，防止每次发布时被篡改哦！





Springboot给我们提供了两种“开机启动”某些方法的方式：ApplicationRunner和CommandLineRunner。
源码在run方法中afterRefresh会取出所有的ApplicationRunner和CommandLineRunner的bean来执行，从代码来看，ApplicationRunner使用的入参是ApplicationArguments，CommandLineRunner使用的入参是args.getSourceArgs()，也就是命令行传来的参数，所以这两种开机启动只是入参不同，其他都是一样的。


SpringBoot中的绝大多数的bean都是在run方法中的refreshContext(context)中初始化并保存的。


SpringBoot提供了SpringApplicationRunListener接口，来在ApplicationContetext初始化各个阶段来扩展



说到 Java 微服务，不得不提及 Spring Boot，它已经变得非常流行。Spring Boot 和 Spring 也是基于 Java，是 Jakarta EE 的竞争对手。Spring Boot 采用了 Dropwizard 和 Pivotal 的“fat jar”概念。Pivotal 是 Spring Boot 背后的公司，正在推动“云原生”一词，这个词最初是由 Netflix 发明的，目前已经在市场上得到广泛使用。



SpringBoot属性配置、读取等
https://www.cnblogs.com/shamo89/p/8178109.html


SpringBoot约定优于配置
https://blog.csdn.net/zhousenshan/article/details/80146569
https://segmentfault.com/a/1190000015069140
https://blog.csdn.net/caychen/article/details/79980415
https://blog.csdn.net/zhangzeyuaaa/article/details/43567135

许多新的框架使用了约定优于配置的方法，包括：Spring，Ruby on Rails，Kohana PHP，Grails，Grok，Zend Framework，CakePHP，symfony，Maven，ASP.NET MVC，Web2py（MVC），Apache Wicket。

约定优于配置（convention over configuration）[1]，也称作按约定编程[2]，是一种软件设计范式，旨在减少软件开发人员需做决定的数量，获得简单的好处，而又不失灵活性。

本质是说，开发人员仅需规定应用中不符约定的部分。例如，如果模型中有个名为Sale的类，那么数据库中对应的表就会默认命名为sales。只有在偏离这一约定时，例如将该表命名为"products_sold"，才需写有关这个名字的配置。

如果您所用工具的约定与你的期待相符，便可省去配置；反之，你可以配置来达到你所期待的方式。



SpringBoot外部配置读取顺序
外部配置加载顺序：
https://blog.csdn.net/caychen/article/details/79981446
https://blog.csdn.net/u010963948/article/details/76550223
https://blog.csdn.net/wu2374633583/article/details/80075198





