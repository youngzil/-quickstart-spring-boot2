package com.quickstart.springboot.webflux.netty;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/8/11 11:18
 */
@Configuration
public class RouterConfig {

  @Autowired
  private TimeHandler timeHandler;

  @Bean
  public RouterFunction<ServerResponse> timerRouter() {
    return
        //route(GET("/time"), timeHandler::getTime)//
        route(GET("/time"), req -> timeHandler.getTime(req))//
            .andRoute(GET("/date"), timeHandler::getDate) // 这种方式相对于上一行更加简洁
            .andRoute(GET("/times"), timeHandler::sendTimePerSec);  // 增加这一行
  }
}
