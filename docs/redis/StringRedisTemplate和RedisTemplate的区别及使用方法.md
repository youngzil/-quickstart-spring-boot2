1、两者的数据是不共通的；
    也就是说StringRedisTemplate只能管理StringRedisTemplate里面的数据，
    RedisTemplate只能管理RedisTemplate中的数据。
2、其实他们两者之间的区别主要在于他们使用的序列化类:
    RedisTemplate使用的是JdkSerializationRedisSerializer    存入数据会将数据先序列化成字节数组然后在存入Redis数据库。 
    StringRedisTemplate使用的是StringRedisSerializer
3、使用时注意事项：
    当你的redis数据库里面本来存的是字符串数据或者你要存取的数据就是字符串类型数据的时候，那么你就使用StringRedisTemplate即可。
    但是如果你的数据是复杂的对象类型，而取出的时候又不想做任何的数据转换，直接从Redis里面取出一个对象，那么使用RedisTemplate是更好的选择。
4、RedisTemplate使用时常见问题：
  　　　　redisTemplate 中存取数据都是字节数组。当redis中存入的数据是可读形式而非字节数组时，使用redisTemplate取值的时候会无法获取导出数据，获得的值为null。可以使用 StringRedisTemplate 试试。


StringRedisTemplate.opsForValue().* //操作String字符串类型
StringRedisTemplate.opsForList().*  //操作List类型
StringRedisTemplate.opsForHash().*  //操作Hash类型
StringRedisTemplate.opsForSet().*  //操作set类型
StringRedisTemplate.opsForZSet().*  //操作有序set
StringRedisTemplate.delete(key/collection) //根据key/keys删除





参考
https://www.cnblogs.com/MyYJ/p/10778874.html
https://blog.csdn.net/FYWT98/article/details/82585583



