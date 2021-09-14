package org.quickstart.springboot.kafka;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api("测试")
@RestController
@RequestMapping("/test")
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    KafkaTemplate kafkaTemplate;

    @ApiOperation("测试kafka")
    @PostMapping("/Kafka")
    @ResponseBody
    public void testkafka() throws Exception {
        kafkaTemplate.send("boot", UUID.randomUUID().toString());
    }
}