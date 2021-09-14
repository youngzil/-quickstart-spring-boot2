package org.quickstart.springboot.kafka.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.UUID;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class TestApplicationTests {

    private final Producer producer;

    @Test
    void contextLoads() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            long id = i+1;
            String name = UUID.randomUUID().toString();
            int num = random.nextInt();
            producer.send(Entity.builder().id(id).name(name).num(num).build());
        }
    }
}
