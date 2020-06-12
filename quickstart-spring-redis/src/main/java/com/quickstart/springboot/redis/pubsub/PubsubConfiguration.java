package com.quickstart.springboot.redis.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/2/17 10:50
 */
@Configuration
//value 是启动类
@Import( value = Bootstrap.class )
public class PubsubConfiguration  {

    @Autowired
    private RedisMessageListener redisMessageListener;

//    @Bean
//    public ChannelTopic expiredTopic() {
//        return new ChannelTopic("__keyevent@0__:expired");
//    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            @Autowired RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
//        redisMessageListenerContainer.addMessageListener(redisMessageListener, expiredTopic());
        redisMessageListenerContainer.addMessageListener(redisMessageListener, new ChannelTopic("__keyevent@0__:expired"));
        return redisMessageListenerContainer;
    }

}

