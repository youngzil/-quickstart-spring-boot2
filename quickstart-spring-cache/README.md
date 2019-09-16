 @Cacheable 注解来缓存方法执行的结果
 @CacheEvict注解,来更新查询方法的结果缓存
 @CachePut 既要保证方法被调用，又希望结果被缓存
 
 三种方式的对比
 @Cacheable、@CachePut、@CacheEvict 注释介绍
 
 @Cacheable 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
 @CachePut 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用
 @CachEvict 主要针对方法配置，能够根据一定的条件对缓存进行清空
 
 
 
参考
https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#cache-jsr-107
https://www.jianshu.com/p/49fc4065201a

