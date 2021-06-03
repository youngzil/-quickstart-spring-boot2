package org.quickstart.springboot.jasypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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

  @Autowired
  private Environment environment;

  @RequestMapping("/get")
  public String get() {

    // 拦截是在getProperty之后，get之前还是密文
    environment.getProperty("encryptedv2.property");

    return property;
  }

}
