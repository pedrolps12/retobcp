package com.bcp.reto.retotecnico.security;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * The type Jwt request.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  private String username;

  @NotNull
  private String password;

}
