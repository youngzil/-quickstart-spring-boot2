package org.quickstart.springboot.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2019/9/16 22:43
 */
@SpringBootApplication
//开启缓存自动配置
@EnableCaching
public class CacheDemoApplication {


  public static void main(String[] args) {
    SpringApplication.run(CacheDemoApplication.class, args);

  }

  //配置cacheManager
  @Bean
  public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {

    RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
    //设置缓存名称
    redisCacheManager.setCacheNames(new ArrayList<String>() {{
      add("UserCache");
    }});
    //设置缓存默认前缀
    redisCacheManager.setCachePrefix(new DefaultRedisCachePrefix("-"));

    //redisCacheManager.setUsePrefix(true);
    redisCacheManager.afterPropertiesSet();
    return redisCacheManager;
  }

  /*
  //使用guava作为缓存
  @Bean
  public CacheManager cacheManager() {
    GuavaCacheManager cacheManager = new GuavaCacheManager("trades");
    cacheManager.setCacheBuilder(CacheBuilder.newBuilder()
        .expireAfterWrite(60 , TimeUnit.SECONDS)
        .maximumSize(1000));
    return cacheManager;
  }

  @Bean
  public CacheManager cacheManager() {
    SimpleCacheManager manager = new SimpleCacheManager();
    List list = new ArrayList();
    list.add(GuavaCache one);
    list.add(GuavaCache two);
    ...
    manager.setCaches(  list )
    return manager;
  }*/

  //配置cache使用过程中的错误处理器
  @Bean
  public CacheErrorHandler cacheErrorHandler() {

    return new CacheErrorHandler() {
      @Override
      public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {

      }

      @Override
      public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {

      }

      @Override
      public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {

      }

      @Override
      public void handleCacheClearError(RuntimeException exception, Cache cache) {

      }
    };
  }

  //配置redis
  @Bean
  public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(connectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    return redisTemplate;
  }


}