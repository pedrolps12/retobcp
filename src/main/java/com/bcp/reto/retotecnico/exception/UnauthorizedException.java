package com.bcp.reto.retotecnico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * The type Unauthorized exception.
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends ApiException {

  /**
   * Instantiates a new Unauthorized exception.
   *
   * @param message the message
   */
  public UnauthorizedException(String message) {
    super(message, "UNAUTHORIZED");
  }
}