package com.quickstart.springboot.webflux.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveSecuredApplication {

  // Then connect to http://localhost:8080/public or http://localhost:8080/private (expects "user" and "password" with basic authentication).


  public static void main(String[] args) {
        SpringApplication.run(ReactiveSecuredApplication.class, args);
    }
}
