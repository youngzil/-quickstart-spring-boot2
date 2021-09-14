package org.quickstart.springboot.kafka;

import com.sun.source.util.TaskListener;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

@Component
public class FilterListener {

    private static final Logger log = LoggerFactory.getLogger(TaskListener.class);

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    // 监听器工厂
    @Autowired
    private ConsumerFactory consumerFactory;

    @Bean
    public NewTopic targetTopic() {
        return new NewTopic("topic-filter", 4, (short)2);
    }

    // 配置一个消息过滤策略
    @Bean
    public ConcurrentKafkaListenerContainerFactory myFilterContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        //配合RecordFilterStrategy使用，被过滤的信息将被丢弃
        factory.setAckDiscarded(true);
        // 消息过滤策略（将消息转换为long类型，判断是奇数还是偶数，把所有奇数过滤，监听器只接收偶数）
        factory.setRecordFilterStrategy(new RecordFilterStrategy() {
            @Override
            public boolean filter(ConsumerRecord consumerRecord) {
                long data = Long.parseLong((String)consumerRecord.value());
                log.info("filterContainerFactory filter : " + data);
                if (data % 2 == 0) {
                    return false;
                }
                //返回true将会被丢弃
                return true;
            }
        });
        return factory;
    }

    // 消费监听
    @KafkaListener(id = "filterCons", topics = "topic03", containerFactory = "myFilterContainerFactory")
    public void filterListener(String data) {
        //这里做数据持久化的操作
        log.error("topic.quick.filter receive : " + data);
    }

}
