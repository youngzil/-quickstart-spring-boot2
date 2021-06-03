package org.quickstart.springboot.redis.common;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/3/5 16:20
 */
@Configuration
public class RedisClusterMultiDatasourceConfig2 {

  @Autowired
  private Environment environment;

  /**
   * 配置lettuce连接池
   */
  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.redis.cluster.lettuce.pool")
  public GenericObjectPoolConfig redisPool() {
    return new GenericObjectPoolConfig();
  }

  /**
   * 配置第一个数据源的
   */
  @Bean("redisClusterConfig")
  @Primary
  public RedisClusterConfiguration redisClusterConfig() {

    Map<String, Object> source = new HashMap<>(8);
    source.put("spring.redis.cluster.nodes", environment.getProperty("spring.redis.cluster.nodes"));
    RedisClusterConfiguration redisClusterConfiguration;
    redisClusterConfiguration = new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
    redisClusterConfiguration.setPassword(environment.getProperty("spring.redis.password"));
    return redisClusterConfiguration;

  }

  /**
   * 配置第一个数据源的连接工厂 这里注意：需要添加@Primary 指定bean的名称，目的是为了创建两个不同名称的LettuceConnectionFactory
   */
  @Bean("lettuceConnectionFactory")
  @Primary
  public LettuceConnectionFactory lettuceConnectionFactory(GenericObjectPoolConfig redisPool,
      @Qualifier("redisClusterConfig") RedisClusterConfiguration redisClusterConfig) {
    LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(redisPool).build();
    return new LettuceConnectionFactory(redisClusterConfig, clientConfiguration);
  }

  /**
   * 配置第一个数据源的RedisTemplate 注意：这里指定使用名称=factory 的 RedisConnectionFactory 并且标识第一个数据源是默认数据源 @Primary
   */
  @Bean("redisTemplate")
  @Primary
  public RedisTemplate redisTemplate(@Qualifier("lettuceConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
    return getRedisTemplate(redisConnectionFactory);

  }

  /**
   * 配置第二个数据源
   */
  @Bean("secondaryRedisClusterConfig")
  public RedisClusterConfiguration secondaryRedisConfig() {

    Map<String, Object> source = new HashMap<>(8);
    source.put("spring.redis.cluster.nodes", environment.getProperty("spring.secondaryRedis.cluster.nodes"));
    RedisClusterConfiguration redisClusterConfiguration;
    redisClusterConfiguration = new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
    redisClusterConfiguration.setPassword(environment.getProperty("spring.redis.password"));

    return redisClusterConfiguration;
  }

  @Bean("secondaryLettuceConnectionFactory")
  public LettuceConnectionFactory secondaryLettuceConnectionFactory(GenericObjectPoolConfig redisPool,
      @Qualifier("secondaryRedisClusterConfig") RedisClusterConfiguration secondaryRedisClusterConfig) {
    LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(redisPool).build();
    return new LettuceConnectionFactory(secondaryRedisClusterConfig, clientConfiguration);
  }

  /**
   * 配置第一个数据源的RedisTemplate 注意：这里指定使用名称=factory2 的 RedisConnectionFactory
   */
  @Bean("secondaryRedisTemplate")
  public RedisTemplate secondaryRedisTemplate(@Qualifier("secondaryLettuceConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
    return getRedisTemplate(redisConnectionFactory);
  }

  private RedisTemplate getRedisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(factory);

    // key采用String的序列化方式
    template.setKeySerializer(RedisSerializer.string());
    // hash的key也采用String的序列化方式
    template.setHashKeySerializer(RedisSerializer.string());
    // value序列化方式采用jackson
    template.setValueSerializer(RedisSerializer.string());
    // hash的value序列化方式采用jackson
    template.setHashValueSerializer(RedisSerializer.string());
    template.afterPropertiesSet();

    return template;
  }

  /*private RedisTemplate getRedisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(factory);

    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(om);

    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    // key采用String的序列化方式
    template.setKeySerializer(stringRedisSerializer);
    // hash的key也采用String的序列化方式
    template.setHashKeySerializer(stringRedisSerializer);
    // value序列化方式采用jackson
    template.setValueSerializer(jackson2JsonRedisSerializer);
    // hash的value序列化方式采用jackson
    template.setHashValueSerializer(jackson2JsonRedisSerializer);
    template.afterPropertiesSet();

    return template;
  }*/

}

