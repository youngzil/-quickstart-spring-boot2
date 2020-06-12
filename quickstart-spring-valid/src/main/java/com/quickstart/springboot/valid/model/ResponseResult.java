package com.quickstart.springboot.valid.model;

import lombok.Data;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/4/20 22:59
 */
@Data
public class ResponseResult {
  private Integer code;
  private String msg;

  public ResponseResult(){
  }

  public ResponseResult(ResultEnum resultEnum){
    this.code = resultEnum.getCode();
    this.msg = resultEnum.getMessage();
  }

  public ResponseResult(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}