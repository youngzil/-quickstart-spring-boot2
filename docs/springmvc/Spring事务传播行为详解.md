Spring在TransactionDefinition接口中规定了7种类型的事务传播行为。
事务传播行为是Spring框架独有的事务增强特性，他不属于的事务实际提供方数据库行为。
这是Spring为我们提供的强大的工具箱，使用事务传播行可以为我们的开发工作提供许多便利。
但是人们对他的误解也颇多，你一定也听过“service方法事务最好不要嵌套”的传言。
要想正确的使用工具首先需要了解工具。本文对七种事务传播行为做详细介绍，内容主要代码示例的方式呈现。


1. 什么是事务传播行为？
事务传播行为用来描述由某一个事务传播行为修饰的方法被嵌套进另一个方法的时事务如何传播。


2. Spring中七种事务传播行为
事务传播行为类型	说明
PROPAGATION_REQUIRED	如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
PROPAGATION_SUPPORTS	支持当前事务，如果当前没有事务，就以非事务方式执行。
PROPAGATION_MANDATORY	使用当前的事务，如果当前没有事务，就抛出异常。

PROPAGATION_REQUIRES_NEW	新建事务，如果当前存在事务，把当前事务挂起。
PROPAGATION_NOT_SUPPORTED	以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
PROPAGATION_NEVER	以非事务方式执行，如果当前存在事务，则抛出异常。

PROPAGATION_NESTED	如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。


总结：
Propagation.REQUIRED
1、在外围方法未开启事务的情况下Propagation.REQUIRED修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。
2、外围方法开启事务的情况下Propagation.REQUIRED修饰的内部方法会加入到外围方法的事务中，所有Propagation.REQUIRED修饰的内部方法和外围方法均属于同一事务，只要一个方法回滚，整个事务均回滚。【只有任何地方抛出异常，都会回滚整个事物】

PROPAGATION_REQUIRES_NEW
1、在外围方法未开启事务的情况下Propagation.REQUIRES_NEW修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。
2、在外围方法开启事务的情况下Propagation.REQUIRES_NEW修饰的内部方法依然会单独开启独立事务，且与外部方法事务也独立，内部方法之间、内部方法和外部方法事务均相互独立，互不干扰。

PROPAGATION_NESTED
1、在外围方法未开启事务的情况下Propagation.NESTED和Propagation.REQUIRED作用相同，修饰的内部方法都会新开启自己的事务，且开启的事务相互独
2、外围方法开启事务的情况下Propagation.NESTED修饰的内部方法属于外部事务的子事务，外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务

总结：
外部未开启事务，都是独立的事务
外部开启事务：1、Propagation.REQUIRED：加入外部事务，是一个事务，内外任何异常都会回滚
            2、PROPAGATION_REQUIRES_NEW：开启独立事务，内外异常只会影响自己本身的
            3、PROPAGATION_NESTED：开启子事务，外部异常影响全部，内部异常只影响本身



参考
https://juejin.im/entry/5a8fe57e5188255de201062b
https://github.com/TmTse/transaction-test


