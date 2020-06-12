package com.quickstart.springboot.valid.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/4/20 22:54
 */
@Data
public class User {


  @NotBlank(message = "姓名不为空")
  private String username;
  @NotBlank(message = "密码不为空")
  private String password;
  // 嵌套必须加 @Valid，否则嵌套中的验证不生效
  @Valid
  @NotNull(message = "userinfo不能为空")
  private UserInfo userInfo;
}
