package com.quickstart.springboot.netty.action.common.res;


import com.quickstart.springboot.netty.action.common.enums.StatusEnum;
import com.quickstart.springboot.netty.action.common.util.StringUtil;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {

  private String code;

  private String message;

  /**
   * 请求号
   */
  private String reqNo;

  private T dataBody;

  public BaseResponse() {
  }

  public BaseResponse(T dataBody) {
    this.dataBody = dataBody;
  }

  public BaseResponse(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public BaseResponse(String code, String message, T dataBody) {
    this.code = code;
    this.message = message;
    this.dataBody = dataBody;
  }

  public static <T> BaseResponse<T> create(T t) {
    return new BaseResponse<T>(t);
  }

  public static <T> BaseResponse<T> create(T t, StatusEnum statusEnum) {
    return new BaseResponse<T>(statusEnum.getCode(), statusEnum.getMessage(), t);
  }

  public static <T> BaseResponse<T> createSuccess(T t, String message) {
    return new BaseResponse<T>(StatusEnum.SUCCESS.getCode(), StringUtil.isNullOrEmpty(message) ? StatusEnum.SUCCESS.getMessage() : message, t);
  }

  public static <T> BaseResponse<T> createFail(T t, String message) {
    return new BaseResponse<T>(StatusEnum.FAIL.getCode(), StringUtil.isNullOrEmpty(message) ? StatusEnum.FAIL.getMessage() : message, t);
  }

  public static <T> BaseResponse<T> create(T t, StatusEnum statusEnum, String message) {

    return new BaseResponse<T>(statusEnum.getCode(), message, t);
  }

}
