package org.quickstart.springboot.example;

import com.wacai.hermes.agent.producer.HermesProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class ExampleController {

    @Autowired
    private HermesProducer producer;

    @GetMapping("/say")
    public String sayHello() {
        return "Hello SpringBoot";
    }
}
