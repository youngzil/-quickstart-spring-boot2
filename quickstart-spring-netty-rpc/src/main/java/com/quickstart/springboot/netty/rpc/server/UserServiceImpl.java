package com.quickstart.springboot.netty.rpc.server;


import com.quickstart.springboot.netty.rpc.client.UserService;

/**
 * Created by zhangshukang.
 */
public class UserServiceImpl implements UserService {

  @Override
  public String callRpc(String param) {
    System.out.println(param);
    return param;
  }
}
