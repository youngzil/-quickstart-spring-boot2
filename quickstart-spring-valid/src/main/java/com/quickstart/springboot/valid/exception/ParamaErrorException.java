package com.quickstart.springboot.valid.exception;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/4/20 22:58
 */
public class ParamaErrorException extends RuntimeException {

  public ParamaErrorException() {
  }

  public ParamaErrorException(String message) {
    super(message);
  }

}
