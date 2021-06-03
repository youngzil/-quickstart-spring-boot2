package org.quickstart.springboot.webflux.netty;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/6/15 17:35
 */
@RestController
@RequestMapping({"test"})
public class BeartbeatController {
  @RequestMapping({"post/beartbeat"})
  public Mono<Void> beartbeat() {
    return Mono.empty();
  }
}

