- [Spring依赖注入的方式](SpringBoot/Spring依赖注入的方式.md)
- [Spring解决循环依赖机制](SpringBoot/Spring解决循环注入的机制.md)
- [BeanFactory和FactoryBean区别](springmvc/BeanFactory和FactoryBean区别.md)
    - Spring中有两种类型的Bean，FactoryBean实现原理
    - BeanFactory和FactoryBean区别
- [Springboot的条件注解](SpringBoot/Springboot的条件注解.md)
- [Spring事务传播行为详解](springmvc/Spring事务传播行为详解.md)
- [Spring AOP切面实现原理](springmvc/Spring AOP.md)
    - Spring AOP中的动态代理主要有两种方式，JDK动态代理和CGLIB动态代理。
- [ContextLoaderListener和DispatchServlet区别](springmvc/ContextLoaderListener和DispatchServlet区别.md)
- [springmvc父子容器](springmvc/springmvc父子容器.md)


- [classpath:与classpath*:的区别](SpringBoot/classpath:与classpath*:的区别.md)
- [Spring的@Value属性注入](SpringBoot/Spring的@Value属性注入.md)
- [SpringBoot项目启动时执行特定方法](SpringBoot/SpringBoot项目启动时执行特定方法.md)
- [SpringBoot缓存实战Caffeine](SpringBoot/SpringBoot缓存实战Caffeine.md)
- [SpringBoot设置跨域](SpringBoot/SpringBoot设置跨域.md)
- [spring-boot-devtools.md](SpringBoot/spring-boot-devtools.md)


- [PropertyPlaceholderConfigurer使用](SpringBoot/PropertyPlaceholderConfigurer使用.md)
- [SpringBoot1学习](SpringBoot/SpringBoot1学习.md)
- [SpringBoot2学习](SpringBoot/SpringBoot2学习.md)
- [SpringBoot四种读取properties文件的方式](SpringBoot/SpringBoot四种读取properties文件的方式.md)
- [SpringBoot学习](SpringBoot/SpringBoot学习.md)
- [SpringBoot异常统一处理](SpringBoot/SpringBoot异常统一处理.md)
- [Springboot打成war包并在tomcat中运行](SpringBoot/Springboot打成war包并在tomcat中运行.md)
- [spring提供的常用接口](SpringBoot/spring提供的常用接口.md)
- [Spring注解](SpringBoot/Spring注解.md)
- [Spring的Aspect的Around表达式](SpringBoot/Spring的Aspect的Around表达式.md)
- [Spring自定义XML标签](SpringBoot/Spring自定义XML标签.md)
- [SpringBoot优雅停机](SpringBoot/SpringBoot优雅停机.md)
- [Spring的IOC流程中核心扩展接口的12个扩展点](SpringBoot/Spring的IOC流程中核心扩展接口的12个扩展点.md)
- [Spring解析ApplicationContext.xml中注入的Bean的解析流程](SpringBoot/Spring解析ApplicationContext.xml中注入的Bean的解析流程.md)
- [使用Jasypt对SpringBoot配置文件加密](SpringBoot/使用Jasypt对SpringBoot配置文件加密.md)
- [启动Spring容器的方式有三种.md](SpringBoot/启动Spring容器的方式有三种)
- [基于Maven的SpringBoot项目实现热部署的两种方式](SpringBoot/基于Maven的SpringBoot项目实现热部署的两种方式.md)
- [多数据源-动态数据源](SpringBoot/多数据源-动态数据源.md)
- [循环依赖问题.md](SpringBoot/循环依赖问题.md)
- [聊聊Spring的bean覆盖（存在同名name、id问题）](SpringBoot/聊聊Spring的bean覆盖（存在同名name、id问题）.md)
- [获取Spring的ApplicationContext](SpringBoot/获取Spring的ApplicationContext.md)
- [获取请求参数的几个注解](SpringBoot/获取请求参数的几个注解.md)
- [项目打包配置](SpringBoot/项目打包配置.md)
- [Bean配置的三种方式（XML、注解、Java类）介绍与对比](SpringBoot/Bean配置的三种方式（XML、注解、Java类）介绍与对比.md)


- [Spring MVC 处理请求的全过程](#Spring-MVC处理请求的全过程)


1、Spring Bean对象
3、Spring Bean的生命周期和常用接口类
6、Spring配置解析文件ApplicationContext.xml
7、自定义命名空间处理器NamespaceHandler和Spring自定义XML标签
java和spring中的上下文Context

加载Bean的方式
DI（IoC）、AOP



[Spring 和 SpringBoot 之间到底有啥区别？](https://cloud.tencent.com/developer/article/1606321)  





使用Spring进行验证和异常处理


aop的拦截好处：
低侵入
低耦合
隔离性


Spring有哪些模块


[Spring过滤器和拦截器执行流程](https://www.jianshu.com/p/394480ae9b7c)  



---------------------------------------------------------------------------------------------------------------------

学习网站
https://mp.weixin.qq.com/s/Xs0C1cCAva9YbYumCXUqnA
https://gitee.com/javacode2018/spring-series

https://blog.csdn.net/qq924862077/category_6754158.html



---------------------------------------------------------------------------------------------------------------------


## Spring MVC处理请求的全过程

Spring一个请求的拦截流程

我们从第一步开始，首先，用户的浏览器发出了一个请求，这个请求经过互联网到达了我们的服务器。

Servlet 容器首先接待了这个请求，并将该请求委托给 DispatcherServlet 进行处理。

1. 接着 DispatcherServlet 将该请求传给了处理器映射组件 HandlerMapping，并获取到适合该请求的拦截器和处理器。在获取到处理器后，DispatcherServlet 还不能直接调用处理器的逻辑，需要进行对处理器进行适配。

2. 处理器适配成功后，DispatcherServlet 通过处理器适配器 HandlerAdapter 调用处理器的逻辑，并获取返回值 ModelAndView。

3. 之后，DispatcherServlet 需要根据 ModelAndView 解析视图。解析视图的工作由 ViewResolver 完成，若能解析成功，ViewResolver 会返回相应的视图对象 View。

4. 在获取到具体的 View 对象后，最后一步要做的事情就是由 View 渲染视图，并将渲染结果返回给用户。



参考  
[Spring MVC 原理探秘 - 一个请求的旅行过程](https://cloud.tencent.com/developer/article/1156025)  


---------------------------------------------------------------------------------------------------------------------
Spring Bean对象


BeanDefinitionHolder：
BeanDefinitionHolder，简单来说其就是一个BeanDefinition的持有者，其定义了一下变量，并对以下变量提供get和set操作
private final BeanDefinition beanDefinition;  
private final String beanName;  
private final String[] aliases; 
BeanDefinitionHolder实现BeanMetadataElement接口
public class BeanDefinitionHolder implements BeanMetadataElement {}

BeanDefinition：
简单来说BeanDefinition接口及其实现类就是bean的所有配置信息的一个数据集合，从类名也可以看出其就是一个bean的定义说明。
BeanDefinition的3个实现类：GenericBeanDefinition、RootBeanDefinition / ChildBeanDefinition
通常来说，使用GenericBeanDefinition类为了注册一个用户可见的bean定义(后置处理器可能会操作它， 甚至可能重新配置父母的名字)。如果父/子关系是预设的建议使用RootBeanDefinition / ChildBeanDefinition。



在spring2.0之前bean只有2种作用域即：singleton(单例)、non-singleton（也称prototype）, Spring2.0以后，增加了session、request、global session三种专用于Web应用程序上下文的Bean

singleton配置实例：
<bean id="role" class="spring.chapter2.maryGame.Role" scope="singleton"/>
或者
<bean id="role" class="spring.chapter2.maryGame.Role" singleton="true"/>

prototype配置实例：
<bean id="role" class="spring.chapter2.maryGame.Role" scope="prototype"/>
或者
<beanid="role" class="spring.chapter2.maryGame.Role" singleton="false"/>



---------------------------------------------------------------------------------------------------------------------
Spring Bean的生命周期和常用接口类

Spring Bean的生命周期：
https://blog.csdn.net/qq924862077/article/details/75043985
Bean的完整生命周期经历了各种方法调用，这些方法可以划分为以下几类：
1、Bean自身的方法　　：　　这个包括了Bean本身调用的方法和通过配置文件中<bean>的init-method和destroy-method指定的方法
2、Bean级生命周期接口方法　　：　　这个包括了BeanNameAware、BeanFactoryAware、InitializingBean和DiposableBean这些接口的方法
3、容器级生命周期接口方法　　：　　这个包括了InstantiationAwareBeanPostProcessor 和 BeanPostProcessor 这两个接口实现，一般称它们的实现类为“后处理器”。还有MergedBeanDefinitionPostProcessor（extends BeanPostProcessor）、BeanFactoryPostProcessor
4、工厂后处理器接口方法　　：　　这个包括了AspectJWeavingEnabler, ConfigurationClassPostProcessor, CustomAutowireConfigurer等等非常有用的工厂后处理器　　接口的方法。工厂后处理器也是容器级的。在应用上下文装配配置文件之后立即调用。



BeanFactoryPostProcessor--提供了对BeanFactory进行操作的处理
BeanPostProcessor--在bean初始化前后做一些额外的操作

起始执行点在AbstractApplicationContext中，invokeBeanFactoryPostProcessors(beanFactory)执行是在初始化Bean实体方法finishBeanFactoryInitialization(beanFactory)之前，这样就可以在初始化bean之前可以对一些bean做一些额外的处理操作。

BeanPostProcessor接口的作用是在Spring容器完成Bean实例化前后可以添加一些自己的逻辑处理，我们可以定义一个或者多个BeanPostProcessor接口的实现。
BeanPostProcessor接口提供了两个方法：
1、postProcessBeforeInitialization  可以对Bean在实例化之前添加一些逻辑处理
2、postProcessAfterInitialization  可以对bean在实例化之后添加一些逻辑处理


---------------------------------------------------------------------------------------------------------------------

Spring配置解析文件ApplicationContext.xml：

ApplicationContext（实现类ClassPathXmlApplicationContext和FileSystemXmlApplicationContext等）
ApplicationContext--》BeanFactory--》XmlBeanDefinitionReader（解析成Document对象）--》BeanDefinitionDocumentReader（转为输入流）--》BeanDefinitionParserDelegate（解析生成BeanDefinitionHolder，其实bean元素的解析的结果是一个BeanDefinition对象，结果是将BeanDefinition注册到BeanFactory中）
1、简单来说在ApplicationContext中所做的操作是初始化了一个BeanFactory和XmlBeanDefinitionReader，
2、其中XmlBeanDefinitionReader是用来解析Spring的xml配置文件的，BeanDefinitionReader所做的处理操作是将配置的ApplicationContext.xml解析成为Document对象，
3、接下来会有BeanDefinitionDocumentReader来对Document进行解析，BeanDefinitionDocumentReader中也没有对xml中的bean元素进行处理操作，其真正的处理操作是在BeanDefinitionParserDelegate中进行处理的，
4、对bean标签解析，最终还是在BeanDefinitionParserDelegate中对元素进行处理解析生成BeanDefinitionHolder，其实bean元素的解析的结果是一个BeanDefinition对象，其包含了所有的bean的属性设置，processBeanDefinition中的BeanDefinitionReaderUtils的处理结果是将BeanDefinition注册到BeanFactory中。
5、首先BeanDefinitionParserDelegate中定义了其会解析xml的spring元素标签，并且还有说明一点的是Spring解析xml的标签是通过命名空间Namespace来决定的，BeanDefinitionParserDelegate中定义了如下命名空间，只会支持解析这个命名空间中的标签元素。
public static final String BEANS_NAMESPACE_URI = "http://www.springframework.org/schema/beans";

Spring配置文件的解析处理操作是在BeanDefinitionParserDelegate中完成，当然也是只是完成了对Beans这个命名空间中的元素的解析处理操作，对于其他的命名空间是通过其他解析器进行解析操作的，
BeanDefinitionParserDelegate做的处理操作就是将bean的各种标签解析成BeanDefinition对象，并组装成BeanDefinitionHolder返回。


---------------------------------------------------------------------------------------------------------------------
自定义命名空间处理器NamespaceHandler和Spring自定义XML标签

---------------------------------------------------------------------------------------------------------------------


java和spring中的上下文Context
参考
http://blog.csdn.net/u013147600/article/details/49616427

在java web中主要的就是ServletContext
一个 WEB 运用程序只有一个 ServletContext 实例, 它是在容器(包括 JBoss, Tomcat 等)完全启动 WEB 项目之前被创建, 生命周期伴随整个 WEB 运用。
 当在编写一个 Servlet 类的时候, 首先是要去继承一个抽象类 HttpServlet, 然后可以直接通过 getServletContext() 方法来获得 ServletContext 对象。
 这是因为 HttpServlet 类中实现了 ServletConfig 接口, 而 ServletConfig 接口中维护了一个 ServletContext 的对象的引用。
 利用 ServletContext 能够获得 WEB 运用的配置信息, 实现在多个 Servlet 之间共享数据等。
 
 
 Spring中主要是ApplicationContext，是BeanFactory的子接口
 
 
 Spring提供的常用的接口和类，在Context的各个阶段进行扩展，都是在Context初始化的时候，在构建beanFactory的时候注入的prepareBeanFactory(beanFactory);，在内部定义了很多Map来保存这些bean。
 
 InstantiationAwareBeanPostProcessor：作用在bean的实例化前后
 BeanFactoryPostProcessor：在Bean创建之前，读取Bean的元属性，并根据自己的需求对元属性进行改变，比如将Bean的scope从singleton改变为prototype
 BeanPostProcessor：作用在bean的初始化前后，通过实现BeanPostProcessor接口允许用户对新建的Bean进行修改。
InitializingBean：通过实现InitializingBean接口可以在BeanFactory 设置所有的属性后作出进一步的反应可以实现该接口
 DisposableBean：提供了一个唯一的方法destory()，在Bean生命周期结束前调用destory()方法做一些收尾工作
 
 BeanNameAware：在Bean加载的过程中可以获取到该Bean的id
BeanFactoryAware：在Bean加载的过程中可以获取到加载该Bean的BeanFactory
 ApplicationContextAware接口的Bean，在Bean加载的过程中可以获取到Spring的ApplicationContext
 BeanClassLoaderAware：获取Bean的类装载器
 
 FactoryBean：它是实现了FactoryBean<T>接口的Bean，根据该Bean的ID从BeanFactory中获取对象，
 
 BeanDefinitionParser接口：配置文件解析，唯一方法parse()
NamespaceHandlerSupport类：配置解析注入，一般重写init()方法

PropertyPlaceholderConfigurer类：用于读取Java属性文件中的属性，然后插入到BeanFactory的定义中
PropertyOverrideConfigurer类：用于读取Java属性文件中的属性，并覆盖XML配置文件中的定义，即PropertyOverrideConfigurer允许XML配置文件中有默认的配置信息
 CustomEditorConfigurer类：可以读取实现java.beans.PropertyEditor接口的类，将字符串转为指定的类型。
 ResourceBundleMessageSource类：提供国际化支持，bean的名字必须为messageSource。此处，必须存在一个名为jdbc的属性文件。
 
 
 
 Spring本质上来说，就是首先解析配置文件或者注解等方式的bean定义，然后实例化，放在beanFactory中，上下文对象ApplicationContext就是BeanFactory接口的子接口，BeanFactory是顶级接口。
 registry阶段可以理解为实例化阶段，构造函数调用构造对象，如AnnotationConfigApplicationContext的this()和register(annotatedClasses)方法
 refresh()阶段可以理解为初始化阶段，属性和对象的赋值构建
 
 
 
 bean初始化过程：
 refresh()--》finishBeanFactoryInitialization(beanFactory);--》beanFactory.preInstantiateSingletons();--》循环beanNames（变量beanDefinitionNames）--》
 
 上述变量变量beanDefinitionNames的值是在AnnotationConfigApplicationContext初始化时候register进来的
 使用 ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);启动时候，在构造方法中
 1、this();--构建AnnotatedBeanDefinitionReader和ClassPathBeanDefinitionScanner，在这两个类中registry所有的bean定义，传递的参数就是对象AnnotationConfigApplicationContext（是BeanDefinitionRegistry接口的实现），类似dubbo利用NamespaceHandlerSupport类初始化解析配置文件一样，利用BeanDefinitionRegistry解析bean定义，只是这里利用AnnotatedBeanDefinitionReader和ClassPathBeanDefinitionScanner
2、register(annotatedClasses);--registry构造函数的参数的class
3、refresh();--执行bean的扩展方法等，如上面 Spring提供的常用的接口和类，在BeanFactory、ApplicationContext、bean的各个阶段执行对应的扩展方法。初始化（先实例化，后初始化，目前是初始化阶段）所有的非懒加载的实例，顺带有注册功能，如invokeBeanFactoryPostProcessors(beanFactory);和finishBeanFactoryInitialization(beanFactory);等
 
 
 
 ---------------------------------------------------------------------------------------------------------------------
 使用Spring进行验证和异常处理
 
 
 参考
 https://medium.com/sprang/validation-and-exception-handling-with-spring-ba44b3ee0723
 
 
 ---------------------------------------------------------------------------------------------------------------------
 
 
 
 
 