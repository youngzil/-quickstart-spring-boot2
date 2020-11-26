主要包括：
一、基于XML的配置方式
二、基于注解的配置方式
三、基于Java类的配置方式


二、基于注解的配置方式
一个component-scan标签下可以有多个include-filter和exclude-filter，

Spring3.0提供了一系列的针对依赖注入的注解，这使得Spring IoC在XML文件之外多了一种可行的选择，主要包含如下注解类型：
1、Bean的定义注解：@Component、@Controller、@Repository、@Service、@Bean
2、Bean的生命周期注解：实现Spring提供的两个接口：initializingBean 和 DisposableBean、
	在XML文件中使用<bean>的init-method 和 destory-method 属性、
	@PostConstruct：初始化之后的执行的回调方法、
	@PreDestroy：销毁之前的回调方法
3、Bean的依赖检查注解：
	使用Spring2.0提供的@Required注解，提供了更细粒度的控制，@Required注解只能标注在Setter方法之上，（标注在其他方法之上会被忽略 ）用于检查其是否被调用，当Setter方法未被调用的话会抛出异常。
4、Bean的自动装配注解：
	@Autowired可以对成员变量、方法和构造函数进行标注，来完成自动装配的工作，他根据类型进行自动装配，如果需要按名称进行装配，则需要配合@Qualifier使用。
	当标注了@Autowired的方法所需的类型在Spring容器中不存在的话会抛出异常


三、基于Java类的配置方式
基于Java类定义Bean配置元数据，其实就是通过Java类定义Spring配置元数据，且直接消除XML配置文件。
首先让我们看一下基于Java类如何定义Bean配置元数据，具体步骤如下：
1、使用@Configuration注解需要作为配置的类，表示该类将定义Bean的元数据
2、使用@Bean注解相应的方法，该方法名默认就是Bean的名称，该方法返回值就是Bean的对象。
3、AnnotationConfigApplicationContext或子类进行加载基于java类的配置



基于Java方式的配置方式不是为了完全替代基于XML方式的配置，两者可以结合使用，因此可以有两种结合使用方式：
在基于Java方式的配置类中引入基于XML方式的配置文件
在基于XML方式的配置文件中中引入基于Java方式的配置


引入基于XML配置文件：
<bean id="message" class="java.lang.String">
    <constructor-arg index="0" value="test"></constructor-arg>
</bean>
 
@Configuration("ctxConfig")
@ImportResource("classpath:com/jike/***/appCtx.xml")
public class ApplicationContextConfig {
  ……
}


引入基于Java的配置文件：
<context:annotation-config/>
<bean id="ctxConfig" class=“com.jike.***..ApplicationContextConfig"/>
 
//测试类
public void testXmlConfig() {
        String configLocations[] = {" classpath:com/jike/***/appCtx.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocations);
         ……
}
可以看到在XML的配置文件当中将java的配置类当中Bean来声明，第一行的是开启注解驱动支持。
值得注意的是必须得配置<context:annotation-config/>在XML配置文件中。



为什么Repository只能标注在DAO类上面呢？
   因为该注解的作用不只是将类识别为bean，同时他还能将所标注的类中所抛出的数据访问异常封装为Spring的数据访问异常类型。Spring本身提供了一个丰富的，并且是与具体的访问技术无关的数据访问异常结构，用于封装不同的持久层框架所抛出的异常，使得异常独立与底层的框架。

Spring2.5在@Repository的基础上增加了功能类似的额外三个注解，总共有如下四种注解：
@Component：一个泛化的概念，表示一个组件（Bean），可作用在任何层次
@Controller：用于对Controller实现类进行标注，目前该功能与Component相同
@Repository：用于对DAO实现类进行标注
@Service：用于对Service实现类进行标注，目前该功能与Component相同



参考
https://blog.csdn.net/icarus_wang/article/details/51649635

