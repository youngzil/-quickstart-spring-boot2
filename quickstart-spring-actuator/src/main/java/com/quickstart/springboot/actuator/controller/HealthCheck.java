package com.quickstart.springboot.actuator.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/9/9 00:10
 */
@Component
public class HealthCheck implements HealthIndicator {

  @Override
  public Health health() {
    int errorCode = check(); // perform some specific health check
    if (errorCode != 0) {
      return Health.down()
          .withDetail("Error Code", errorCode).build();
    }
    return Health.up().build();
  }

  public int check() {
    // Our logic to check health
    return 0;
  }
}
