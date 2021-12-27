package com.bcp.reto.retotecnico.repository;

import com.bcp.reto.retotecnico.entity.User;
import io.reactivex.rxjava3.core.Maybe;
import java.util.UUID;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

/**
 * The interface User repository.
 */
public interface UserRepository extends RxJava3CrudRepository<User, UUID> {

  /**
   * Find by username maybe.
   *
   * @param username the username
   * @return the maybe
   */
  Maybe<User> findByUsername(String username);

}
