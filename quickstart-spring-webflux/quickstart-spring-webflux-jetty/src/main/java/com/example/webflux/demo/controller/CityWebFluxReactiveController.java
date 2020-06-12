package com.example.webflux.demo.controller;

import com.example.webflux.demo.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2019/11/23 22:23
 */
@RestController
@RequestMapping(value = "/city2")
public class CityWebFluxReactiveController {

  /**
   *
   * 持 Reactive 的操作类为 ReactiveRedisTemplate
   * @Autowired 注入 ReactiveRedisTemplate 对象。
  ReactiveValueOperations 是 String（或 value）的操作视图，
  操作视图还有 ReactiveHashOperations、ReactiveListOperations、ReactiveSetOperations 和 ReactiveZSetOperations 等。
  不一样的是，操作视图 set 方法是操作 City 对象，但可以 get 回 Mono 或者 Flux 对象
   */
  @Autowired
  private ReactiveRedisTemplate reactiveRedisTemplate;

  @GetMapping(value = "/{id}")
  public Mono<City> findCityById(@PathVariable("id") Long id) {
    String key = "city_" + id;
    ReactiveValueOperations<String, City> operations = reactiveRedisTemplate.opsForValue();
    Mono<City> city = operations.get(key);
    return city;
  }

  @PostMapping
  public Mono<City> saveCity(@RequestBody City city) {
    String key = "city_" + city.getId();
    ReactiveValueOperations<String, City> operations = reactiveRedisTemplate.opsForValue();
    return operations.getAndSet(key, city);
  }

  @DeleteMapping(value = "/{id}")
  public Mono<Long> deleteCity(@PathVariable("id") Long id) {
    String key = "city_" + id;
    return reactiveRedisTemplate.delete(key);
  }
}
