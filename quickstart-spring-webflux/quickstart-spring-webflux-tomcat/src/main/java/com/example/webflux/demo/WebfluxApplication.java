package com.example.webflux.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableReactiveMongoRepositories
public class WebfluxApplication {

  // http://localhost:8080/
  // http://127.0.0.1:8080/days
  public static void main(String[] args) {
    SpringApplication.run(WebfluxApplication.class, args);
  }
}
