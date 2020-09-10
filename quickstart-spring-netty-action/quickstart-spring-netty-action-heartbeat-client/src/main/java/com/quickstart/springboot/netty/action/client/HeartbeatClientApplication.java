package com.quickstart.springboot.netty.action.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author crossoverJie
 */
@SpringBootApplication
@EnableOpenApi
public class HeartbeatClientApplication {

  private final static Logger LOGGER = LoggerFactory.getLogger(HeartbeatClientApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(HeartbeatClientApplication.class);
    LOGGER.info("启动 Client 成功");
  }

}



