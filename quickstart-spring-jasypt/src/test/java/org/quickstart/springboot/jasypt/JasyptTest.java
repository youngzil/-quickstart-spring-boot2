package org.quickstart.springboot.jasypt;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Test;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/7/21 19:55
 */
public class JasyptTest {

  @Test
  public void test() {

    BasicTextEncryptor encryptor = new BasicTextEncryptor();
    //加密所需的salt(盐)
    encryptor.setPassword("G0CvDz7oJn6");
    //要加密的数据（数据库的用户名或密码）
    String username = encryptor.encrypt("root");
    String password = encryptor.encrypt("root123");
    System.out.println("username:" + username);
    System.out.println("password:" + password);
  }

}