package org.quickstart.springboot.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JasyptApplication {

  public static void main(String[] args) {
    SpringApplication.run(JasyptApplication.class, args);
  }


  @Bean("jasyptStringEncryptor")
  public StringEncryptor stringEncryptor() {
    return new MyPBEStringEncryptor();
  }

}
