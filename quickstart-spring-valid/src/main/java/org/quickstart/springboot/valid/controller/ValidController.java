package org.quickstart.springboot.valid.controller;

import org.quickstart.springboot.valid.exception.ParamaErrorException;
import org.quickstart.springboot.valid.model.ResponseResult;
import org.quickstart.springboot.valid.model.ResultEnum;
import org.quickstart.springboot.valid.model.User;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/4/20 22:55
 */
@RestController
public class ValidController {

  /**
   * 获取用户信息
   *
   * @param username 姓名
   * @return ResponseResult
   */
  @Validated
  @GetMapping("/user/{username}")
  public ResponseResult findUserInfo(@PathVariable String username) {
    if (username == null || "".equals(username)) {
      throw new ParamaErrorException("username 不能为空");
    }
    return new ResponseResult(ResultEnum.SUCCESS);
  }


  /**
   * 新增用户
   *
   * @param user 用户信息
   * @return ResponseResult
   */
  @PostMapping("/user2/add")
  public ResponseResult addUserInfo(@Valid @RequestBody User user) {
    return new ResponseResult(ResultEnum.SUCCESS);
  }

}
