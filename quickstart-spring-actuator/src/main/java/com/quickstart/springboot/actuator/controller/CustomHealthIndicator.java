package com.quickstart.springboot.actuator.controller;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/9/9 00:31
 */
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

  @Override
  protected void doHealthCheck(Health.Builder builder) throws Exception {
    // 使用builder 来创建健康状态信息
    // 如果你throw 了一个 exception，那么status 就会被置为DOWN，异常信息会被记录下来
    builder.up()
        .withDetail("app", "这个项目很健康")
        .withDetail("error", "Nothing, I'm very good");
  }
}
