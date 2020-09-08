package com.quickstart.springboot.actuator.controller;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Version: 1.0
 * @Desc:
 */
@RestController
public class HelloController {

  @Resource
  private PrometheusCustomMonitor monitor;

  @GetMapping("/hello")
  public String hello() {
    return "Hello Spring Boot Actuator2";
  }

  @GetMapping("/slow")
  public String slow() throws InterruptedException {
    Random random = new Random();
    int delay = random.nextInt(10);
    TimeUnit.SECONDS.sleep(delay);
    return "sleep time: " + delay;
  }

  @RequestMapping("/order")
  public String order(@RequestParam(defaultValue = "0") String flag) throws Exception {
    monitor.getOrderCount().increment();
    if ("1".equals(flag)) {
      throw new Exception("出错啦");
    }
    Random random = new Random();
    int amount = random.nextInt(100);
    monitor.getAmountSum().record(amount);
    return "下单成功, 金额: " + amount;
  }
}
