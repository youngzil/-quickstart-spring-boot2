AOP 即 Aspect Oriented Program 面向切面编程



首先，在面向切面编程的思想里面，把功能分为核心业务功能，和周边功能。
- 所谓的核心业务，比如登陆，增加数据，删除数据都叫核心业务
- 所谓的周边功能，比如性能统计，日志，事务管理等等

周边功能在 Spring 的面向切面编程AOP思想里，即被定义为切面

在面向切面编程AOP的思想里面，核心业务功能和切面功能分别独立进行开发，然后把切面功能和核心业务功能 "编织" 在一起，这就叫AOP



AOP的目的  
AOP能够将那些与业务无关，却为业务模块所共同调用的逻辑或责任（例如事务处理、日志管理、权限控制等）封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可拓展性和可维护性。



AOP 当中的概念：  
- 切入点（Pointcut）
    在哪些类，哪些方法上切入（where）
- 通知（Advice）
    在方法执行的什么实际（when:方法前/方法后/方法前后）做什么（what:增强的功能）
- 切面（Aspect）
    切面 = 切入点 + 通知，通俗点就是：在什么时机，什么地方，做什么增强！
- 织入（Weaving）
    把切面加入到对象，并创建出代理对象的过程。（由 Spring 来完成）



Spring AOP代理对象的生成  
Spring提供了两种方式来生成代理对象: JDKProxy和Cglib，具体使用哪种方式生成由AopProxyFactory根据AdvisedSupport对象的配置来决定。  
默认的策略是如果目标类是接口，则使用JDK动态代理技术，否则使用Cglib来生成代理。


Spring AOP中的动态代理主要有两种方式，JDK动态代理和CGLIB动态代理。

JDK动态代理通过反射来接收被代理的类，并且要求被代理的类必须实现一个接口。  
JDK动态代理的核心是InvocationHandler接口和Proxy类。  
如果目标类没有实现接口，那么Spring AOP会选择使用CGLIB来动态代理目标类。

CGLIB（Code Generation Library），是一个代码生成的类库，可以在运行时动态的生成某个类的子类，注意，CGLIB是通过继承的方式做的动态代理，因此如果某个类被标记为final，那么它是无法使用CGLIB做动态代理的。





[Spring(4)——面向切面编程（AOP模块）](https://www.jianshu.com/p/994027425b44)  
[Spring AOP原理分析一次看懂](https://zhuanlan.zhihu.com/p/36617574)  
[Spring AOP实现原理](https://juejin.cn/post/6844903604118093831)  
[完全读懂Spring框架之AOP实现原理](https://my.oschina.net/guangshan/blog/1797461)  
[]()  
[]()  
[]()  







