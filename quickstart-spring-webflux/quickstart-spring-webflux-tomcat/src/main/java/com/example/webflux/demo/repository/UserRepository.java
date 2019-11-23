package com.example.webflux.demo.repository;

import com.example.webflux.demo.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

  public Mono<User> getUserById(String id);

  public Flux<User> getAllUsers();

  public Mono<Void> saveUser(Mono<User> user);

  public Mono<User> putUser(String id, Mono<User> user);

  public Mono<String> deleteUser(String id);
}