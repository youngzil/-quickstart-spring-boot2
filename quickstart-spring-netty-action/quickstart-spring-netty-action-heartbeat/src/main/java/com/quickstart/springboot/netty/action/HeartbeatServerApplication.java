package com.quickstart.springboot.netty.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author crossoverJie
 */
@SpringBootApplication
@Slf4j
public class HeartbeatServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(HeartbeatServerApplication.class, args);
    log.info("启动 Server 成功");
  }

}