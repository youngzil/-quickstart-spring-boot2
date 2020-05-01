聊聊Spring的bean覆盖（存在同名name/id问题），介绍Spring名称生成策略接口BeanNameGenerator【享学Spring】


众所周知，Spring容器可以简单粗暴的把它理解为一个大大的Map，存储着容器所管理的所有的单实例对象。我们从使用getBean(String beanName)方法，根据bean名称就能获得容器内唯一的Bean实例就能“证明”到这一点。

可你是否曾想过：既然它是Map，那万一我们写的@Bean的beanName重名了怎么办呢？Spring框架是怎么来处理这个事的呢？

Spring容器通俗描述
我们把它理解成一个Map，那Map里面的key-value你应该知道：
key：beanName
value：单例bean对象


在我们现在的Spring应用中，动不动容器就需要管理上千个Bean（更有甚者在过去的one in all应用中可能出现上万个Bean的情况）。
 既然Spring容器是个Map，那key的重要性不言而喻，他指向着全局唯一的Bean实例，若key被覆盖了，就相当于Map的key被覆盖一样，旧的value值可能将永远就触达不到了~

从而可见，确保beanName的唯一性意义重大。但是呢管理的Bean多了，怎么去确保这件事肯定就成了一个难题，那么接下来就了解一下Spring它是怎么造的~



beanName的生成规则
我把beanName的生成规则放在最开始描述，是因为我觉得既然涉及到beanName，那么首先就应该知道beanName是怎么来的。
我们知道，Spring提供了非常非常多的方式允许我们向容器内注册一个Bean，下面总结出常用的注册Bean方式对应的BeanName如下：

1、xml的<bean/>标签方式，由id属性决定（若没指定则为全类名）
2、@Component模式注解方式（包含其所有派生注解如@Service、@Configuration等等）。指定了value值就是value值，否则是类名首字母小写
 1. 此种方式是最为常用，也是大批量注册Bean的首选方式
3、@Bean方式。指定了value值就是它，否则就是方法名
4、FactoryBean方式。（它其实需要结合上面任意一种方式使用，beanName请参考如上描述）
5、。。。

这是一个基本的结论。其实大多数时候我们自己都并不会去指定beanName，若没有自己没有指定的话，它怎么来的呢？Spring对它的生成有什么规律可循呢？那么接下来就就研究下这个策略：名称生成策略

BeanNameGenerator
为bean定义生成bean名称的策略接口。
BeanNameGenerator接口位于 org.springframework.beans.factory.support 包下面，只声明了一个方法，接受两个参数：definition 被生成名字的BeanDefinition实例；registry 生成名字后注册进的BeanDefinitionRegistry。

BeanNameGenerator有两个实现版本，DefaultBeanNameGenerator和AnnotationBeanNameGenerator。
其中DefaultBeanNameGenerator是给资源文件加载bean时使用（BeanDefinitionReader中使用）；
AnnotationBeanNameGenerator是为了处理注解生成bean name的情况。

DefaultBeanNameGenerator
它是用来处理xml资源文件的Bean name生成器

AnnotationBeanNameGenerator
javadoc的描述：它能够处理@Component以及它所有的派生注解，并且还支持JavaEE的javax.annotation.@ManagedBean、以及JSR 330的javax.inject.@Named注解。
如果注解不指定bean名称，则将基于类的短名称（小写的第一个字母）生成适当的名称。


同名name覆盖的case演示
由于Spring给我们提供了非常多的方式来定义Bean，所以势必会出现同名Bean的情况，下面举两个例子来感受一把：


case1：同一个配置文件内出现同名Bean
 使用@Bean若不指定value值，默认是方法名。但因为同一个类内方法名不能一样（不考虑重载情况），所以此处用手工指定同一个value值模拟
得出结论：同一个配置文件内同名的Bean，以最上面定义的为准

case2：不同配置文件内出现同名Bean
得出结论：不同配置文件中存在同名Bean，后解析的配置文件会覆盖先解析的配置文件。
此处需要注意的是：配置文件的先后顺序其实会受到@Order来控制，只是若没有@Order注解的话就按照传入的顺序执行解析。
 比如上例中传入顺序是TempConfig，再RootConfig，倘若在RootConfig头上加注解@Order(1)那么它就会被先解析，所以这时候最终结果是TempConfig配置类上的Bean生效，输出：Person{name='TempConfig----Bean', age=18}

case3：同文件中ComponentScan和@Bean出现同名Bean
得出结论：同文件下@Bean的会生效，@ComponentScan扫描进来不会生效
 最后你会发现：通过@ComponentScan扫描进来的优先级是最低的，原因就是它扫描进来的Bean定义是最先被注册的~

上面case都是描绘的在同一个Spring容器的情况下出现同名的情况，那么若在不同的容器内出现同名Bean呢？
可见如果在不同容器内，即使Bean名称相同，它们也是能够和谐共存的。（@Autowired的时候请注意父子容器问题~）

原因简单解释：每个DefaultListableBeanFactory都持有自己的beanDefinitionNames，然后每个容器在初始化refresh()的时候就是按照这个一个一个进行实例化的，所以不同容器之间即使出现同名的BeanName，也不会互相干扰。它的getBean()基本逻辑是：如果本容器自己有，就不会去父容器里寻觅~

此书提示一点，在有些组件比如BeanPostProcessor需要提前初始化的时候，会调用此方法：
String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);
获取到容器内所有的BeanPostProcessor名称后，挨个getBean()。此处需要注意一点：所有的getBeanNamesForType()方法默认都是不会去父容器里查找的，并且大多数情况下只会处理top-level的类。因此：你若在父容器里定义了一个BeanPostProcessor处理器，它对子容器是不生效的哦~~~

 注意：SpringBoot下因为主容器没有主次之分，粗暴可以理解成它只存在一个容器，因此一般都不会存在此类问题





参考
https://cloud.tencent.com/developer/article/1497702



