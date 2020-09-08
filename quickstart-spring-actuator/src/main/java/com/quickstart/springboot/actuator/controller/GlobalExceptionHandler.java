package com.quickstart.springboot.actuator.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/9/9 00:27
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  @Resource
  private PrometheusCustomMonitor monitor;

  @ResponseBody
  @ExceptionHandler(value = Exception.class)
  public String handle(Exception e) {
    monitor.getRequestErrorCount().increment();
    return "error, message: " + e.getMessage();
  }
}
