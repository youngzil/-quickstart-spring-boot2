package com.example.webflux.demo.repository.impl;

import com.example.webflux.demo.model.User;
import com.example.webflux.demo.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private Map<String, User> users = new HashMap<>();

  @PostConstruct
  public void init() throws Exception {
    users.put(String.valueOf(1), new User("1", "Jack", "Smith", 20));
    users.put(String.valueOf(2), new User("2", "Peter", "Johnson", 25));
  }

  @Override
  public Mono<User> getUserById(String id) {
    return Mono.just(users.get(id));
  }

  @Override
  public Flux<User> getAllUsers() {
    return Flux.fromIterable(this.users.values());
  }

  @Override
  public Mono<Void> saveUser(Mono<User> monoUser) {
    Mono<User> userMono = monoUser.doOnNext(user -> {
      // do post
      users.put(user.getId(), user);

      // log on console
      System.out.println("########### POST:" + user);
    });

    return userMono.then();
  }

  @Override
  public Mono<User> putUser(String id, Mono<User> monoUser) {
    Mono<User> userMono = monoUser.doOnNext(user -> {
      // reset user.Id
      user.setId(id);

      // do put
      users.put(id, user);

      // log on console
      System.out.println("########### PUT:" + user);
    });

    return userMono;
  }

  @Override
  public Mono<String> deleteUser(String id) {
    // delete processing
    users.remove(id);
    return Mono.just("Delete Succesfully!");
  }
}