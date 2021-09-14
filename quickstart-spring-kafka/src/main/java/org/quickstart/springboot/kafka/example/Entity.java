package org.quickstart.springboot.kafka.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Entity {
    private long id;
    private String name;
    private int num;
}
