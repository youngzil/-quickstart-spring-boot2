package com.quickstart.springboot.jasypt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/7/21 19:45
 */
@RestController
public class JasyptController {

  @Value("${encryptedv2.property}")
  private String property;

  @RequestMapping("/get")
  public String get() {
    return property;
  }

}
