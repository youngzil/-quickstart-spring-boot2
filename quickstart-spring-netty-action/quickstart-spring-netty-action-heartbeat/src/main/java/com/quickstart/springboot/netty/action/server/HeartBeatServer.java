package com.quickstart.springboot.netty.action.server;

import com.alibaba.fastjson.JSON;
import com.quickstart.springboot.netty.action.common.pojo.CustomProtocol;
import com.quickstart.springboot.netty.action.init.HeartbeatInitializer;
import com.quickstart.springboot.netty.action.util.NettySocketHolder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import java.net.InetSocketAddress;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Function:
 *
 * @author crossoverJie Date: 21/05/2018 00:30
 * @since JDK 1.8
 */
@Component
public class HeartBeatServer {

  private final static Logger LOGGER = LoggerFactory.getLogger(HeartBeatServer.class);

  private EventLoopGroup boss = new NioEventLoopGroup();
  private EventLoopGroup work = new NioEventLoopGroup();


  @Value("${netty.server.port}")
  private int nettyPort;


  /**
   * 启动 Netty
   */
  @PostConstruct
  public void start() throws InterruptedException {

    ServerBootstrap bootstrap = new ServerBootstrap()
        .group(boss, work)
        .channel(NioServerSocketChannel.class)
        .localAddress(new InetSocketAddress(nettyPort))
        //保持长连接
        .childOption(ChannelOption.SO_KEEPALIVE, true)
        .childHandler(new HeartbeatInitializer());

    ChannelFuture future = bootstrap.bind().sync();
    if (future.isSuccess()) {
      LOGGER.info("启动 Netty 成功");
    }
  }


  /**
   * 销毁
   */
  @PreDestroy
  public void destroy() {
    boss.shutdownGracefully().syncUninterruptibly();
    work.shutdownGracefully().syncUninterruptibly();
    LOGGER.info("关闭 Netty 成功");
  }


  /**
   * 发送消息
   */
  public void sendMsg(CustomProtocol customProtocol) {
    NioSocketChannel socketChannel = NettySocketHolder.get(customProtocol.getId());

    if (null == socketChannel) {
      throw new NullPointerException("没有[" + customProtocol.getId() + "]的socketChannel");
    }

    ChannelFuture future = socketChannel.writeAndFlush(Unpooled.copiedBuffer(customProtocol.toString(), CharsetUtil.UTF_8));
    future.addListener((ChannelFutureListener) channelFuture ->
        LOGGER.info("服务端手动发消息成功={}", JSON.toJSONString(customProtocol)));
  }
}
