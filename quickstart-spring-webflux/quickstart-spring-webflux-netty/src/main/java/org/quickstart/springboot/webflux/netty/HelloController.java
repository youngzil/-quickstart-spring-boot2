package org.quickstart.springboot.webflux.netty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/8/11 11:39
 */
@RestController
public class HelloController {

  @GetMapping("/hello")
  public Mono<String> hello() {   // 【改】返回类型为Mono<String>
    return Mono.just("Welcome to reactive world ~");     // 【改】使用Mono.just生成响应式数据
  }

}
