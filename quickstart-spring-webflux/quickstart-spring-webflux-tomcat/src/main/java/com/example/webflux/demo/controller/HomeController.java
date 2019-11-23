/*
 * Copyright 2017-2019 Pamarin.com
 */
package com.example.webflux.demo.controller;

import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author jitta
 */
@Slf4j
@RestController
public class HomeController {

  @GetMapping({"", "/hello"})
  public Mono<String> hello(ServerWebExchange exchange) {
    ServerHttpRequest request = exchange.getRequest();
    log.debug("path => {}", request.getPath().value());
    exchange.getSession().subscribe(session -> {
      log.debug("session Id => {}", session.getId());
    });
    return Mono.just("Hello world.");
  }

  @GetMapping("/days")
  public Flux<String> days() {
    return Flux.fromIterable(Arrays.asList(
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday"
    ));
  }

  @GetMapping("/months")
  public Mono<List> months() {
    return Mono.just(Arrays.asList(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    ));
  }

}
