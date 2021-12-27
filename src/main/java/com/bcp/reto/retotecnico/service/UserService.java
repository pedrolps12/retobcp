package com.bcp.reto.retotecnico.service;

import com.bcp.reto.retotecnico.security.JwtRequest;
import com.bcp.reto.retotecnico.security.JwtResponse;
import io.reactivex.rxjava3.core.Maybe;

/**
 * The interface User service.
 */
public interface UserService {

  /**
   * Load user by username maybe.
   *
   * @param request the request
   * @return the maybe
   */
  Maybe<JwtResponse> loadUserByUsername(JwtRequest request);

}
