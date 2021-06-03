package org.quickstart.springboot.webflux.netty;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/8/11 11:29
 */
public class BeartbeatControllerTest {

  @Test
  public void beartbeat() throws InterruptedException {

    WebClient webClient = WebClient.create("http://localhost:8090");   // 1
    Mono<String> resp = webClient
        .get().uri("/api/hello") // 2
        .retrieve() // 3
        .bodyToMono(String.class);  // 4
    resp.subscribe(System.out::println);    // 5
    TimeUnit.SECONDS.sleep(1);  // 6

  }

}