package com.quickstart.springboot.netty.action.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Function:
 *
 * @author crossoverJie Date: 2017/6/7 下午11:28
 * @since JDK 1.8
 */
@Data
public class BaseRequest {

  @ApiModelProperty(required = false, value = "唯一请求号", example = "1234567890")
  private String reqNo;

  @ApiModelProperty(required = false, value = "当前请求的时间戳", example = "0")
  private int timeStamp;

  public BaseRequest() {
    this.setTimeStamp((int) (System.currentTimeMillis() / 1000));
  }

}
