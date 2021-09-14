package org.quickstart.springboot.kafka.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    @KafkaListener(topics = Constants.TOPIC,groupId = Constants.GROUP_ID)
    public void consume(Entity entity)
    {
        log.info("Consume a entity, id is "+entity.getId());
    }
}
