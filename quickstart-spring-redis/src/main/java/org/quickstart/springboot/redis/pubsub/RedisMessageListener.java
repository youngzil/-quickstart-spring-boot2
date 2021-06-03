package org.quickstart.springboot.redis.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/2/17 10:50
 */
@Component
public class RedisMessageListener implements MessageListener {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    //key 过期时调用
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("onPMessage pattern " + pattern + " " + " " + message);
        String channel2 = new String(message.getChannel());
        String str = (String) redisTemplate.getValueSerializer().deserialize(message.getBody());
        System.out.println(str);

        byte[] body = message.getBody();// 建议使用: valueSerializer
        byte[] channel = message.getChannel();
        System.out.print("onMessage >> " );
        System.out.println(String.format("channel: %s, body: %s, bytes: %s"
                ,new String(channel), new String(body), new String(pattern)));

    }

}




