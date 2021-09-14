package org.quickstart.springboot.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * 接受kafka消息的类
 */
public class MyKafkaListeners {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = {"boot"})
    public void processMessage(String content) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("接收信息");
        System.out.println(content);
    }
}
