package com.quickstart.springboot.webflux.undertow;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/6/15 17:34
 */
@RestController
@RequestMapping({"api"})
public class ApiController {

  @PostMapping({"tastpost"})
  public Mono<Map> tastpost(@RequestBody Map<String, Object> boy) {
    Map<String, Object> object = new HashMap<>(16);
    object.put("respCode", Integer.valueOf(200));
    object.put("respMessage", "successful");
    object.put("result", boy);
    return Mono.just(object);
  }


  @GetMapping({"get"})
  public Mono<Map> tastpost() {
    Map<String, Object> object = new HashMap<>(16);
    object.put("respCode", Integer.valueOf(200));
    object.put("respMessage", "successful");
    object.put("result", "boy");
    return Mono.just(object);
  }

}