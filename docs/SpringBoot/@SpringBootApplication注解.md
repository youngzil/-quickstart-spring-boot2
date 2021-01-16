@SpringBootApplication是一个复合注解，包括@SpringBootConfiguration，@EnableAutoConfiguration和@ComponentScan

springboot是通过注解@EnableAutoConfiguration的方式，去查找，过滤，加载所需的configuration,@ComponentScan扫描我们自定义的bean,@SpringBootConfiguration使得被@SpringBootApplication注解的类声明为注解类．

其实就是SpringBoot通过根据配置文件自动装配所属依赖的类，再用动态代理的方式注入到Spring的IoC容器中


- @SpringBootConfiguration继承自@Configuration，二者功能也一致，标注当前类是配置类，并会将当前类内声明的一个或多个以@Bean注解标记的方法的实例纳入到srping容器中，并且实例名就是方法名。
- @EnableAutoConfiguration的作用启动打开SpringBoot的自动配置机制，@EnableAutoConfiguration注解的意思就是Springboot根据你添加的jar包来配置你项目的默认配置，比如根据spring-boot-starter-web ，来判断你的项目是否需要添加了webmvc和tomcat，就会自动的帮你配置web项目中所需要的默认配置
    - @AutoConfigurationPackage 的作用是将主配置类所在的包作为自动配置的包进行管理
    - @Import({AutoConfigurationImportSelector.class}) 导入一个类到IoC容器中，导入什么类呢，是根据META-INF下面的spring.factories的配置进行导入的
- @ComponentScan，扫描当前包及其子包下被@Component，@Controller，@Service，@Repository注解标记的类并纳入到spring容器中进行管理。是以前的<context:component-scan>（以前使用在xml中使用的标签，用来扫描包配置的平行支持）。
   




SpringBootApplication注解中4个方法
- Class<?>[] exclude() default {}:根据class来排除,排除特定的类加入spring容器，传入参数value类型是class类型。
- String[] excludeName() default {}:根据class name来排除,排除特定的类加入spring容器，传入参数value类型是class的全类名字符串数组。
- String[] scanBasePackages() default {}:指定扫描包，参数是包名的字符串数组。
- Class<?>[] scanBasePackageClasses() default {}:扫描特定的包，参数类似是Class类型数组。


指定扫描路径 或者 排除加载特定的类


参考  
[springboot快速入门及@SpringBootApplication注解分析](https://www.jianshu.com/p/4e1cab2d8431)  
[springboot系列文章之SpringBootApplication注解](https://juejin.cn/post/6844903661269696519)  
[简单讲讲@SpringBootApplication](https://www.jianshu.com/p/39ee4f98575c)  


