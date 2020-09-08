package com.quickstart.springboot.netty.rpc;


import com.quickstart.springboot.netty.rpc.client.nettyClientScan.EnableNettyRpcClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by zhangshukang.
 */

@SpringBootApplication
@EnableNettyRpcClient(basePackages = {"com.quickstart.springboot.netty.rpc"})
public class NettyRpcSpringBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(NettyRpcSpringBootApplication.class);
  }

}

