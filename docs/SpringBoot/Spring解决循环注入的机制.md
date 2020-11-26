我们在此处回顾一下单例bean的生命周期：
1、实例化单例bean；
2、注入依赖；
3、重写相应的方法能够获取bean的名字；
4、重写相应的方法能够获取beanfactory实例；
5、重写相应的方法能够在bean定义前执行；
6、重写相应的方法能够在bean定义时执行；
7、重写相应的方法在bean定义后执行；
8、重写相应的方法在bean销毁时执行；



循环注入的发生有三种情形：
1、构造器注入；
2、单例bean的setter注入；
3、多例bean的setter注入；


再描述第一种情况：
1、当发生构造器的循环注入时，对象都依赖于对方进行实例化，也就都无法生成实例化对象，所以无法注入，大罗神仙也帮不了；
2、而当发生单例bean循环注入的时候，Spring提供一个提前曝光的策略，解决这个问题，后头讲解；
3、Spring并不会管理多例bean的生命周期，也不会提曝光多例bean，当注入一圈下来，对象C会发现对象A还在创建，所以，无法实现。


首先，需要明确的是spring对循环依赖的处理有三种情况：
- ①构造器的循环依赖：这种依赖spring是处理不了的，直 接抛出BeanCurrentlylnCreationException异常。
- ②单例模式下的setter循环依赖：通过“三级缓存”处理循环依赖。
- ③非单例循环依赖：无法处理。



spring单例对象的初始化大略分为三步：
1. createBeanInstance：实例化，其实也就是调用对象的构造方法实例化对象
2. populateBean：填充属性，这一步主要是多bean的依赖属性进行填充
3. initializeBean：调用spring xml中的init 方法。




Spring为了解决单例的循环依赖问题，使用了三级缓存(三个Map)。

```
/** Cache of singleton objects: bean name –> bean instance */
private final Map singletonObjects = new ConcurrentHashMap(256);
/** Cache of singleton factories: bean name –> ObjectFactory */
private final Map> singletonFactories = new HashMap>(16);
/** Cache of early singleton objects: bean name –> bean instance */
private final Map earlySingletonObjects = new HashMap(16);
```
这三级缓存(Map)的作用分别是：
- singletonFactories ： 进入实例化阶段的单例对象工厂的cache （三级缓存）
- earlySingletonObjects ：完成实例化但是尚未初始化的，提前暴光的单例对象的Cache （二级缓存）
- singletonObjects：完成初始化的单例对象的cache（一级缓存）




Spring只能解决单例模式下的属性注入的循环依赖，而不能解决prototype，以及构造方法的循环依赖。


单例bean通过setXxx或者@Autowired进行循环依赖：简单来说就是对象通过构造函数初始化之后就暴露到容器中，这样就不会存在循环初始化对象的情况了
Spring通过setXxx或者@Autowired方法解决循环依赖其实是通过提前暴露一个ObjectFactory对象来完成的，简单来说ClassA在调用构造器完成对象初始化之后，在调用ClassA的setClassB方法之前就把ClassA实例化的对象通过ObjectFactory提前暴露到Spring容器中。

构造方法不支持循环依赖的原因在于：Spring解决循环依赖依靠的是Bean的“半成品”，这个半成品指的是已经实例化，但还没初始化的状态，依赖引用传递(当然java是值传递)传递给需要使用该bean的另外一个bean。而构造方法是完成实例化必须的，所以构造器的循环依赖无法解决。  
单例bean构造器参数循环依赖：Spring在创建构造器循环依赖时其实就是循环初始化操作 A-> B -> A  当A要被初始化第二次时就直接抛出异常BeanCurrentlyInCreationException

非单例循环依赖  
对于“prototype”作用域bean, Spring 容器无法完成依赖注入，因为Spring 容器不进行缓 存“prototype”作用域的bean ，因此无法提前暴露一个创建中的bean 。  
Spring不支持原型bean的循环依赖。对于原型bean的初始化过程中不论是通过构造器参数循环依赖还是通过setXxx方法产生循环依赖，Spring都会直接报错处理BeanCurrentlyInCreationException


Spring采用在实例化之后，提前曝光bean的方法，结合三级缓存来实现单例模式下的属性注入的循环依赖。




Bean对象循环依赖问题处理：
1、Spring不支持原型bean的循环依赖。对于原型bean的初始化过程中不论是通过构造器参数循环依赖还是通过setXxx方法产生循环依赖，Spring都会直接报错处理BeanCurrentlyInCreationException
2、单例bean 构造器参数循环依赖：Spring在创建构造器循环依赖时其实就是循环初始化操作 A-> B -> A  当A要被初始化第二次时就直接抛出异常BeanCurrentlyInCreationException
3、单例bean通过setXxx或者@Autowired进行循环依赖：简单来说就是对象通过构造函数初始化之后就暴露到容器中，这样就不会存在循环初始化对象的情况了
Spring通过setXxx或者@Autowired方法解决循环依赖其实是通过提前暴露一个ObjectFactory对象来完成的，简单来说ClassA在调用构造器完成对象初始化之后，在调用ClassA的setClassB方法之前就把ClassA实例化的对象通过ObjectFactory提前暴露到Spring容器中。




参考  
[Spring解决循环依赖机制](https://blog.csdn.net/hanxiaobo521/article/details/106619725)  
[Spring解决循环注入的机制](https://blog.csdn.net/that_is_cool/article/details/81042068)  
[spring是如何解决循环依赖的？](https://juejin.cn/post/6844903806757502984)  






