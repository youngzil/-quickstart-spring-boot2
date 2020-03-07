StringRedisTemplate.opsForValue().* //操作String字符串类型
StringRedisTemplate.opsForList().*  //操作List类型
StringRedisTemplate.opsForHash().*  //操作Hash类型
StringRedisTemplate.opsForSet().*  //操作set类型
StringRedisTemplate.opsForZSet().*  //操作有序set

StringRedisTemplate.delete(key/collection) //根据key/keys删除


stringRedisTemplate.opsForValue().set("test", "100",60*10,TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间
stringRedisTemplate.opsForValue().get("test")//根据key获取缓存中的val 
stringRedisTemplate.hasKey("546545");//检查key是否存在，返回boolean值
stringRedisTemplate.delete("test");//根据key删除缓存 

stringRedisTemplate.opsForSet().add("red_123", "1","2","3");//向指定key中存放set集合 
stringRedisTemplate.opsForSet().isMember("red_123", "1")//根据key查看集合中是否存在指定数据  
stringRedisTemplate.opsForSet().members("red_123");//根据key获取set集合
stringRedisTemplate.opsForSet().add("red_123", "1","2","3");//向指定key中存放set集合 

stringRedisTemplate.boundValueOps("test").increment(-1);//val做-1操作  
stringRedisTemplate.boundValueOps("test").increment(1);//val +1  

stringRedisTemplate.getExpire("test")//根据key获取过期时间  
stringRedisTemplate.getExpire("test",TimeUnit.SECONDS)//根据key获取过期时间并换算成指定单位  
stringRedisTemplate.expire("red_123",1000 , TimeUnit.MILLISECONDS);//设置过期时间 
 




参考
https://blog.csdn.net/lonely_bin/article/details/100137626
https://blog.csdn.net/liubenlong007/article/details/86477692
https://blog.csdn.net/zhufengyan521521/article/details/89971273
https://blog.csdn.net/FYWT98/article/details/82585583
