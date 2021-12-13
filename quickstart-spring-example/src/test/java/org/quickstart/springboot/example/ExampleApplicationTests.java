package org.quickstart.springboot.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExampleApplicationTests {

    @Autowired
    private ExampleController exampleController;

    @Test
    void contextLoads() {
        String say = exampleController.sayHello();
        System.out.println(say);
    }

}
