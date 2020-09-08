package com.quickstart.springboot.netty.rpc.client;

import com.quickstart.springboot.netty.rpc.client.nettyClientScan.NettyRpcClient;

/**
 * Created by zhangshukang.
 */

@NettyRpcClient
public interface UserService {
    String callRpc(String param); //5
}
