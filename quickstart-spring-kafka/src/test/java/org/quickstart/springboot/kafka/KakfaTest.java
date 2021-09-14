package org.quickstart.springboot.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class KakfaTest {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    // 发送消息
    @Test
    public void testFilter() throws InterruptedException {
        kafkaTemplate.send("topic03", System.currentTimeMillis() + "");
    }

    // 消息过滤器
    // 消息过滤器可以在消息抵达监听容器前被拦截，过滤器根据系统业务逻辑去筛选出需要的数据再交由KafkaListener处理。
    // 配置消息其实是非常简单的额，只需要为监听容器工厂配置一个RecordFilterStrategy(消息过滤策略)，返回true的时候消息将会被抛弃，返回false时，消息能正常抵达监听容器。
    // 这里我们将消息转换为long类型，判断该消息为基数还是偶数，把所有基数过滤，监听容器只接收偶数。
}
