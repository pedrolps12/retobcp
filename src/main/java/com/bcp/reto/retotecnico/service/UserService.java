package com.bcp.reto.retotecnico.service;

import com.bcp.reto.retotecnico.security.JwtRequest;
import com.bcp.reto.retotecnico.security.JwtResponse;
import io.reactivex.rxjava3.core.Maybe;

public interface UserService {

  Maybe<JwtResponse> loadUserByUsername(JwtRequest request);

}
