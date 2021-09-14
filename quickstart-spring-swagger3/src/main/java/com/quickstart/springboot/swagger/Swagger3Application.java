package com.quickstart.springboot.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class Swagger3Application {

  // 启动应用！访问swagger页面：http://localhost:8080/swagger-ui/index.html

  public static void main(String[] args) {
    SpringApplication.run(Swagger3Application.class, args);
  }

}
