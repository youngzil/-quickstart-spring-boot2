package com.quickstart.springboot.webflux.netty;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/8/11 11:39
 */
public class HelloControllerTest {

  @Test
  public void hello() throws InterruptedException {

    WebClient webClient = WebClient.create("http://localhost:8090");   // 1
    Mono<String> resp = webClient
        .get().uri("/time") // 2
        .retrieve() // 3
        .bodyToMono(String.class);  // 4
    resp.subscribe(System.out::println);    // 5
    TimeUnit.SECONDS.sleep(1);  // 6
  }

  @Test
  public void webClientTest3() throws InterruptedException {
    WebClient webClient = WebClient.create("http://localhost:8090");
    webClient
        .get().uri("/times")
        .accept(MediaType.TEXT_EVENT_STREAM)    // 1
        .retrieve()
        .bodyToFlux(String.class)
        .log()  // 2
        .take(10)   // 3
        .blockLast();
  }

}