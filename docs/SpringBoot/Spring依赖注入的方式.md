Spring依赖注入的方式

其实就是下面的4种，配置方式分两种：注解和配置文件


一、注解注入

注解注入在Spring中是用的最多的一种方式，就是在java代码中使用注解的方式进行装配，在代码中加入@Resource或者@Autowired、

在Java代码中可以使用@Autowired或@Resource注解方式进行Spring的依赖注入。

两者的区别是：@Autowired默认按类型装配，@Resource默认按名称装配，当找不到与名称匹配的bean时，才会按类型装配。  
@Autowired：基于 类型 的自动装配注入  
@Resource：基于 名称 的自动装配注入  

就是配置文件的依赖注入—自动装配  
依赖注入—自动装配:<bean id="***" class="***" autowire="byType">,只需要配置一个autowire属性即可完成自动装配，不用再配置文件中写<property>,但是在类中还是要生成依赖对象的setter方法。


二、构造器注入

构造器注入指的是在类中构造带参数的构造方法实现类的注入，举例：在类SpringAction中要依赖两个对象SpringDao和User类的对象，那么我们就可以在SpringAction中进行构造器注入，也就是说在创建SpringAction对象时要将SpringDao和User两个参数值传进来：


三、set注入

使用属性的setter方法注入  
setter注入指的是在类中加入setter方法实现类的注入，


四、静态工厂的方法注入

静态工厂顾名思义，就是通过调用静态工厂的方法来获取自己需要的对象，为了让spring管理所有对象，我们不能直接通过"工程类.静态方法()"来获取对象，而是依然通过spring注入的形式获取：


五、动态工厂的方式注入

与静态工厂方式注入的方法不同，它需要先创建工厂类再调用方法



在Spring容器中为一个bean配置依赖注入有三种方式：
· 使用属性的setter方法注入  这是最常用的方式；<property name="userDao" ref="userDao">
· 使用构造器注入；<constructor-arg index="0" type="com.aptech.dao.PersonDAO"ref="personDao"></constructor-arg>  
· 使用Filed注入（用于注解方式）:在代码中加入@Resource或者@Autowired
· 依赖注入—自动装配:<bean id="***" class="***" autowire="byType">,只需要配置一个autowire属性即可完成自动装配，不用再配置文件中写<property>,但是在类中还是要生成依赖对象的setter方法。





参考  
[spring中依赖注入方式总结](https://blog.csdn.net/baidu_32739019/article/details/83657760)  
[Spring依赖注入的四种方式（重点是注解方式）](https://blog.csdn.net/cheetahlover/article/details/51600991)  
[Spring装配Bean的三种方式+导入和混合配置](https://www.cnblogs.com/summerday152/p/12655615.html#%E5%9F%BA%E4%BA%8Ejava%E7%9A%84%E6%98%BE%E5%BC%8F%E8%A3%85%E9%85%8D)  



