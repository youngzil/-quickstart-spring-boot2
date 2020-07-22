package com.quickstart.springboot.webflux.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;
import reactor.netty.http.server.HttpServer;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/7/23 00:15
 */

//SSL启动
/*@Component
public class NettyWebServerFactorySslCustomizer
    implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {

  @Override
  public void customize(NettyReactiveWebServerFactory serverFactory) {
    Ssl ssl = new Ssl();
    ssl.setEnabled(true);
    ssl.setKeyStore("classpath:sample.jks");
    ssl.setKeyAlias("alias");
    ssl.setKeyPassword("password");
    ssl.setKeyStorePassword("secret");
    Http2 http2 = new Http2();
    http2.setEnabled(false);
    serverFactory.addServerCustomizers(new SslServerCustomizer(ssl, http2, null));
    serverFactory.setPort(8443);
  }
}*/
// 自定义配置
// @Component
public class NettyWebServerFactoryPortCustomizer implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {

  @Value("${server.port:8099}")
  private int port;

  @Override
  public void customize(NettyReactiveWebServerFactory serverFactory) {
    // 第一种方式
    serverFactory.setPort(port);
    // 或者
    // 第二种方式
    // serverFactory.addServerCustomizers(new PortCustomizer(8088));
  }

  private static class PortCustomizer implements NettyServerCustomizer {

    private final int port;

    private PortCustomizer(int port) {
      this.port = port;
    }

    @Override
    public HttpServer apply(HttpServer httpServer) {
      return httpServer.port(port);
    }
  }

  private static class EventLoopNettyCustomizer implements NettyServerCustomizer {

    @Override
    public HttpServer apply(HttpServer httpServer) {
      EventLoopGroup parentGroup = new NioEventLoopGroup();
      EventLoopGroup childGroup = new NioEventLoopGroup();
      return httpServer.tcpConfiguration(tcpServer -> tcpServer
          .bootstrap(serverBootstrap -> serverBootstrap
              .group(parentGroup, childGroup)
              .channel(NioServerSocketChannel.class)));
    }
  }

  // 第三种方式
  /*@Bean
  public NettyReactiveWebServerFactory nettyReactiveWebServerFactory() {
    NettyReactiveWebServerFactory webServerFactory = new NettyReactiveWebServerFactory();
    webServerFactory.addServerCustomizers(new EventLoopNettyCustomizer());
    return webServerFactory;
  }*/

}




