1、Springboot的条件注解
2、使用@ComponentScan注解用来扫描加排除
3、spring启动执行某些特定方法
4、SpringBoot使用spring-boot-maven-plugin插件打包，实际是通过org.springframework.boot.loader.JarLauncher来加载jar的
5、Springboot中使用aop导致@Autowired全部注入失败 和 Spring AOP注解详解











---------------------------------------------------------------------------------------------------------------------
https://blog.csdn.net/qq_36722039/article/details/81572399

可以使用@ComponentScan注解用来扫描加排除，不加ComponentScan注解，springboot是默认扫描springboot启动类所在的包及其子包，我们现在自己扫描，然后使用@ComponentScan注解的excludeFilters属性用来排除不想扫描的bean


---------------------------------------------------------------------------------------------------------------------
spring启动执行某些特定方法：
Springboot给我们提供了两种“开机启动”某些方法的方式：ApplicationRunner和CommandLineRunner。
Spring的配置文件的init-method
继承某些接口方法，实例化的时候，会执行某些特定方法

---------------------------------------------------------------------------------------------------------------------




SpringBoot使用spring-boot-maven-plugin插件打包，实际是通过org.springframework.boot.loader.JarLauncher来加载jar的

<build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.6.0</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
  
  

Manifest-Version: 1.0
Implementation-Title: AIFGateway/Security/Client
Implementation-Version: 1.0
Start-Class: com.pobo.spring.security.demo.Application
Spring-Boot-Classes: BOOT-INF/classes/
Spring-Boot-Lib: BOOT-INF/lib/
Build-Jdk-Spec: 1.8
Spring-Boot-Version: 2.1.6.RELEASE
Created-By: Maven Archiver 3.4.0
Main-Class: org.springframework.boot.loader.JarLauncher




---------------------------------------------------------------------------------------------------------------------

Springboot中使用aop导致@Autowired全部注入失败 和 Spring AOP注解详解


springboot使用aop拦截controller干一些事导致service们@Autowired全部注入失败，拦截所有controller之后发现 service都注入失败

因为：
jdk是代理接口，私有方法必然不会存在在接口里，所以就不会被拦截到； 
cglib是子类，private的方法照样不会出现在子类里，也不能被拦截。 


解决方案 这个aop只能适用于 protect 和public 修饰符修饰的方法
之后把controller中的所有方法都改成public

原来AOP只能对public 和provide 生效，如果你的方法限制是private，那么service注入就为空，在springboot 中默认使用的是cglib来代理操作对象，首先，私有方法是不会出现在代理类中，这也就是为什么代理对象无法对private操作的根本原因
1、jdk是代理接口，私有方法必然不会存在在接口里，所以就不会被拦截到； 
2、cglib是子类，private的方法照样不会出现在子类里，也不能被拦截。 
解决的根本办法不是强制使用cglib来代理，而是要将你的controller中的方法不设置私有属性



下面需要对SpringBoot AOP 中一些注解了解一下
@Aspect:描述一个切面类，定义切面类的时候需要打上这个注解

@Configuration：spring-boot配置类

@Pointcut：声明一个切入点，切入点决定了连接点关注的内容，使得我们可以控制通知什么时候执行。Spring AOP只支持Spring bean的方法执行连接点。所以你可以把切入点看做是Spring bean上方法执行的匹配。一个切入点声明有两个部分：一个包含名字和任意参数的签名，还有一个切入点表达式，该表达式决定了我们关注那个方法的执行。

注：作为切入点签名的方法必须返回void 类型

Spring AOP支持在切入点表达式中使用如下的切入点指示符：　　　　

execution - 匹配方法执行的连接点，这是你将会用到的Spring的最主要的切入点指示符。

within - 限定匹配特定类型的连接点（在使用Spring AOP的时候，在匹配的类型中定义的方法的执行）。

this - 限定匹配特定的连接点（使用Spring AOP的时候方法的执行），其中bean reference（Spring AOP 代理）是指定类型的实例。

target - 限定匹配特定的连接点（使用Spring AOP的时候方法的执行），其中目标对象（被代理的应用对象）是指定类型的实例。

args - 限定匹配特定的连接点（使用Spring AOP的时候方法的执行），其中参数是指定类型的实例。

@target - 限定匹配特定的连接点（使用Spring AOP的时候方法的执行），其中正执行对象的类持有指定类型的注解。

@args - 限定匹配特定的连接点（使用Spring AOP的时候方法的执行），其中实际传入参数的运行时类型持有指定类型的注解。

@within - 限定匹配特定的连接点，其中连接点所在类型已指定注解（在使用Spring AOP的时候，所执行的方法所在类型已指定注解）。

@annotation - 限定匹配特定的连接点（使用Spring AOP的时候方法的执行），其中连接点的主题持有指定的注解。

其中execution使用最频繁，即某方法执行时进行切入。定义切入点中有一个重要的知识，即切入点表达式，我们一会在解释怎么写切入点表达式。

切入点意思就是在什么时候切入什么方法，定义一个切入点就相当于定义了一个“变量”，具体什么时间使用这个变量就需要一个通知。

即将切面与目标对象连接起来。

如例子中所示，通知均可以通过注解进行定义，注解中的参数为切入点。

spring aop支持的通知：

@Before：前置通知：在某连接点之前执行的通知，但这个通知不能阻止连接点之前的执行流程（除非它抛出一个异常）。

@AfterReturning ：后置通知：在某连接点正常完成后执行的通知，通常在一个匹配的方法返回的时候执行。




参考
https://blog.csdn.net/Yue_zuozuo/article/details/85053971
https://blog.csdn.net/wangh92/article/details/79581129
https://blog.csdn.net/qq_28024699/article/details/80595501


---------------------------------------------------------------------------------------------------------------------




---------------------------------------------------------------------------------------------------------------------



---------------------------------------------------------------------------------------------------------------------




---------------------------------------------------------------------------------------------------------------------



---------------------------------------------------------------------------------------------------------------------






---------------------------------------------------------------------------------------------------------------------





---------------------------------------------------------------------------------------------------------------------




---------------------------------------------------------------------------------------------------------------------



---------------------------------------------------------------------------------------------------------------------




