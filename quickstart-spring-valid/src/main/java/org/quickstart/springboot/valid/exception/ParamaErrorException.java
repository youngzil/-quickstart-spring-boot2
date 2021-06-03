package org.quickstart.springboot.valid.exception;

/**
 * @author yangzl
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
