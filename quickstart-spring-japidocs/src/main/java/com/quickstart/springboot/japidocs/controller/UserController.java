package com.quickstart.springboot.japidocs.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> User API </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/8/13 14:32
 */
@RequestMapping("/api/user/")
@RestController
public class UserController {

  /**
   * Get User List
   */
  @RequestMapping(path = "list", method = {RequestMethod.GET, RequestMethod.POST})
  public List<String> list() {
    return null;
  }

  /**
   * Save User
   */
  @PostMapping(path = "save")
  public UserVO saveUser(@RequestBody UserVO userVO) {
    return null;
  }

  /**
   * Delete User
   *
   * @param userId user id
   */
  @PostMapping("delete")
  public boolean deleteUser(@RequestParam Long userId) {
    return Boolean.TRUE;
  }

}
