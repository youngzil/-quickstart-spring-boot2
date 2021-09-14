package org.quickstart.springboot.kafka.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
//防止出现Field injection not recommended警告，代替了原来的直接在字段上@Autowired
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Producer {

    private final KafkaTemplate kafkaTemplate;

    public void send(Entity entity) {
        //发送消息
        //类型一般为String+自定义消息内容，String代表消息Topic，这里消息内容用Entity表示
        ListenableFuture future = kafkaTemplate.send(Constants.TOPIC, entity);
        //回调函数
        future.addCallback(new ListenableFutureCallback<SendResult>() {

            @Override
            public void onFailure(Throwable throwable) {
                log.info("Send message failed");
            }

            @Override
            public void onSuccess(SendResult stringEntitySendResult) {
                log.info("Send message success");
            }
        });
    }
}
