package com.bcp.reto.retotecnico.service.impl;

import com.bcp.reto.retotecnico.entity.User;
import com.bcp.reto.retotecnico.repository.UserRepository;
import com.bcp.reto.retotecnico.security.JwtRequest;
import com.bcp.reto.retotecnico.security.JwtResponse;
import com.bcp.reto.retotecnico.security.TokenProvider;
import com.bcp.reto.retotecnico.service.UserService;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Class User service.
 */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private PasswordEncoder passwordEncoder;

  private TokenProvider tokenProvider;

  /**
   * Instantiates a new User service.
   *
   * @param userRepository  the user repository
   * @param tokenProvider   the token provider
   * @param passwordEncoder the password encoder
   */
  public UserServiceImpl(UserRepository userRepository, TokenProvider tokenProvider,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public Maybe<JwtResponse> loadUserByUsername(JwtRequest request)
      throws UsernameNotFoundException {

    return userRepository.findByUsername(request.getUsername()).switchIfEmpty(
        Maybe.error(new UsernameNotFoundException(
            "User not found with username: " + request.getUsername())))
        .map(user -> User.builder().username(user.getUsername()).password(user.getPassword())
            .rol("ADMIN").build())
        .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
        .map(userDetails -> tokenProvider.generateToken(userDetails))
        .map(token -> JwtResponse.builder().token(token).build());
  }

}
