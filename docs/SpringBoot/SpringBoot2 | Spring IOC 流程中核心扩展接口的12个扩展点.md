
SpringBoot2 | Spring IOC 流程中核心扩展接口的12个扩展点

可以这么理解：
把 Spring 容器理解为一个钥匙环，上面挂满了钥匙，每个钥匙理解为一个扩展接口。钥匙的顺序是固定的，可理解为接口的调用顺序固定，对修改关闭。每个钥匙可以用来做不同的事情，可理解为扩展接口的不同实现，对扩展开放。



Spring 提供了各种丰富的扩展接口，本篇主要对 IOC 过程中涉及的扩展接口做个整理。

1、BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry   动态注册beanDefinition
2、BeanFactoryPostProcessor.postProcessBeanFactory   动态修改BeanDefinition
3、InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation    用来获取 bean，如果获取到，则不再执行对应 bean的初始化之前流程，直接执行后面要讲的postProcessAfterInitialization方法。
4、SmartInstantiationAwareBeanPostProcessor.determineCandidateConstructors
5、MergedBeanDefinitionPostProcessor.postProcessMergedBeanDefinition
6、InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation
7、SmartInstantiationAwareBeanPostProcessor.getEarlyBeanReference
8、InstantiationAwareBeanPostProcessor.postProcessPropertyValues
9、ApplicationContextAwareProcessor.invokeAwareInterfaces
10、BeanFactoryPostProcessor.postProcessBeforeInitialization
11、InitializingBean.afterPropertiesSet
12、BeanFactoryPostProcessor.postProcessAfterInitialization



参考
http://springcloud.cn/view/429



---------------------------------------------------------------------------------------------------------------------





