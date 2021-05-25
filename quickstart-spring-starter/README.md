- [标准的starter流程和模块](#标准的starter流程和模块)
- [注解方式实现依赖加载](#注解方式实现依赖加载)

---------------------------------------------------------------------------------------------------------------------

## 标准的starter流程和模块
通常一个完整的 starter 需要包含下面两个组件:
1、Auto-Configure Module
2、Starter Module

Auto-Configure Module
Auto-Configure Module (自动配置模块) 是包含自动配置类的 Maven 或 Gradle 模块。通过这种方式，我们可以构建可以自动贡献于应用程序上下文的模块，以及添加某个特性或提供对某个外部库的访问

Starter Module
Spring Boot Starter 是一个 Maven 或 Gradle 模块，其唯一目的是提供 "启动" 某个特性所需的所有依赖项。可以包含一个或多个 Auto-Configure Module (自动配置模块)的依赖项，以及可能需要的任何其他依赖项。这样，在Spring 启动应用程序中，我们只需要添加这个 starter 依赖就可以使用其特性

Spring 官方参考手册建议将自动配置分离，并将每个自动配置启动到一个独立的 Maven 或 Gradle 模块中，从而将自动配置和依赖项管理分离开来。如果你没有建立一个供成千上万用户使用的开源库，也可以将二者合并到一个 module 中



命名
来自 Spring 官方的 starter 都是 以 spring-boot-starter 开头，比如:
spring-boot-starter-web
spring-boot-starter-aop

如果我们自定义 starter 功能名称叫acme，那么我们的命名是这样的:
acme-spring-boot-starter
acme-spring-boot-autoconfigure

如果 starter 中用到了配置 keys，也要注意不要使用 Spring Boot 使用的命名空间，比如(server，management，spring)




Spring 启动时会在其 classpath 中所有的 spring.factoreis文件，并加载里面的声明配置，GreetingAutoConfiguration 类就绪后，我们的 Spring Boot Starter 就有了一个自动激活的入口点

Spring Boot 是如何加载spring.factories这个文件并找到我们的配置类的

打开 SpringFactoriesLoader 类，是 SPI 的一种加载方式



这里推荐查看 mybatis-spring-boot-starter 这个非 Spring 官方的案例，从中我们:
模仿其目录结构
模仿其设计理念
模仿其编码规范



参考
https://mp.weixin.qq.com/s/mC2b2foU9Ov5prHm8A-WDQ







---------------------------------------------------------------------------------------------------------------------

## 注解方式实现依赖加载


1. 启动类上加上注解@EnableIdcRegistery 
2. 注解依赖某个类
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Import({IdcRegistryHandler.class})
    public @interface EnableIdcRegistery {
    
    }
3. 依赖类implements ApplicationListener，在onApplicationEvent方法中处理注解相关的逻辑
   public class IdcRegistryHandler implements ApplicationListener<RegisteryEvent> {


在一些业务场景中，当容器初始化完成之后，需要处理一些操作，比如一些数据的加载、初始化缓存、特定任务的注册等等。这个时候我们就可以使用Spring提供的ApplicationListener来进行操作。

在spring中InitializingBean接口也提供了类似的功能，只不过它进行操作的时机是在所有bean都被实例化之后才进行调用。根据不同的业务场景和需求，可选择不同的方案来实现。




