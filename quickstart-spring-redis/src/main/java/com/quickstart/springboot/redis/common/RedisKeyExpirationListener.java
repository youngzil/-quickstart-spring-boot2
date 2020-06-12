package com.quickstart.springboot.redis.common;

//import io.lettuce.core.RedisClient;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/2/18 16:34
 */
//@Component
//@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

//    @Autowired
//    private RedisClient redisClient;
    /**
     * Creates new {@link MessageListener} for {@code __keyevent@*__:expired} messages.
     *
     * @param listenerContainer must not be {@literal null}.
     */
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
        System.out.println("expiredKey=" + expiredKey);
        if(expiredKey.startsWith("zeus:order")){
            //TODO
        }
    }
}
