package com.bcp.reto.retotecnico.expose;

import com.bcp.reto.retotecnico.security.JwtRequest;
import com.bcp.reto.retotecnico.security.JwtResponse;
import com.bcp.reto.retotecnico.service.UserService;
import io.reactivex.rxjava3.core.Single;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/retotecnico")
@CrossOrigin(maxAge = 3600)
public class AuthController {

  private UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(
      value = "/authenticate",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Single<JwtResponse> createToken(
      @Valid @RequestBody JwtRequest jwtRequest) {
    return userService.loadUserByUsername(jwtRequest).toSingle();
  }


}
