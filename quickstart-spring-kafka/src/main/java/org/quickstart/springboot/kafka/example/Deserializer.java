package org.quickstart.springboot.kafka.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Deserializer implements org.apache.kafka.common.serialization.Deserializer<Entity> {
    @Override
    public Entity deserialize(String topic, byte[] data) {
        try {
            return data == null ? null : new ObjectMapper().readValue(data, Entity.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Can not deserialize entity in Deserializer");
        }
        return null;
    }
}
