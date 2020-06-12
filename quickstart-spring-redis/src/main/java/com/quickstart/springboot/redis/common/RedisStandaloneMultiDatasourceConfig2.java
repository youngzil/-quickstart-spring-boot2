package com.asiainfo.aifgw.security.common.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/3/5 15:33
 */
// @Configuration
public class RedisStandaloneMultiDatasourceConfig2 {

  /**
   * 配置lettuce连接池
   */
  @Bean
  @ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
  public GenericObjectPoolConfig redisPool() {
    return new GenericObjectPoolConfig<>();
  }

  /**
   * 配置第一个数据源的
   */
  @Bean
  @ConfigurationProperties(prefix = "spring.redis")
  public RedisStandaloneConfiguration redisConfig() {
    return new RedisStandaloneConfiguration();
  }

  /**
   * 配置第二个数据源
   */
  @Bean
  @ConfigurationProperties(prefix = "spring.redis2")
  public RedisStandaloneConfiguration redisConfig2() {
    return new RedisStandaloneConfiguration();
  }

  /**
   * 配置第一个数据源的连接工厂 这里注意：需要添加@Primary 指定bean的名称，目的是为了创建两个不同名称的LettuceConnectionFactory
   */
  @Bean("factory")
  @Primary
  public LettuceConnectionFactory factory(GenericObjectPoolConfig config, RedisStandaloneConfiguration redisConfig) {
    LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(config).build();
    return new LettuceConnectionFactory(redisConfig, clientConfiguration);
  }

  @Bean("factory2")
  public LettuceConnectionFactory factory2(GenericObjectPoolConfig config, RedisStandaloneConfiguration redisConfig2) {
    LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(config).build();
    return new LettuceConnectionFactory(redisConfig2, clientConfiguration);
  }

  /**
   * 配置第一个数据源的RedisTemplate 注意：这里指定使用名称=factory 的 RedisConnectionFactory 并且标识第一个数据源是默认数据源 @Primary
   */
  @Bean("redisTemplate")
  @Primary
  public RedisTemplate<String, String> redisTemplate(@Qualifier("factory") RedisConnectionFactory factory) {
    return getStringStringRedisTemplate(factory);
  }

  /**
   * 配置第一个数据源的RedisTemplate 注意：这里指定使用名称=factory2 的 RedisConnectionFactory
   */
  @Bean("redisTemplate2")
  public RedisTemplate<String, String> redisTemplate2(@Qualifier("factory2") RedisConnectionFactory factory2) {
    return getStringStringRedisTemplate(factory2);
  }

  /**
   * 设置序列化方式 （这一步不是必须的）
   */
  private RedisTemplate<String, String> getStringStringRedisTemplate(RedisConnectionFactory factory) {
    StringRedisTemplate template = new StringRedisTemplate(factory);
    template.setKeySerializer(RedisSerializer.string());
    template.setValueSerializer(RedisSerializer.string());
    // template.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
    template.setHashKeySerializer(RedisSerializer.string());
    template.setHashValueSerializer(RedisSerializer.string());
    // template.setHashValueSerializer(new FastJsonRedisSerializer<>(Object.class));
    return template;
  }
}
