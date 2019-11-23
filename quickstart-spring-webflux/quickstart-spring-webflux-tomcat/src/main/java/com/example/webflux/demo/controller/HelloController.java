package com.example.webflux.demo.controller;

import com.example.webflux.demo.service.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/23 19:59
 */
@RestController
public class HelloController {

  @GetMapping({"", "/"})
  public Mono<String> hello() {
    return Mono.just("Hello world.");
  }


  @Autowired
  private UserHandler userHandler;

  @GetMapping("/all")
  public Mono<ServerResponse> getAllTweets(ServerRequest request) {
    return userHandler.getAll(request);
  }

}
