package com.quickstart.springboot.jasypt;

import org.jasypt.encryption.pbe.PBEStringCleanablePasswordEncryptor;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/7/21 21:03
 */

public final class MyPBEStringEncryptor implements PBEStringCleanablePasswordEncryptor {

  @Override
  public String encrypt(String s) {
    return "呵呵呵";
  }

  @Override
  public String decrypt(String s) {
    return "哈哈哈";
  }

  @Override
  public void setPasswordCharArray(char[] chars) {
  }

  @Override
  public void setPassword(String s) {
  }

}
