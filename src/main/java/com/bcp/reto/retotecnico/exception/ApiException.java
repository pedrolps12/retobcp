package com.bcp.reto.retotecnico.exception;

import lombok.Getter;


/**
 * The type Api exception.
 */
public class ApiException extends RuntimeException {

  /**
   * The Error code.
   */
  @Getter
  protected final String errorCode;

  /**
   * Instantiates a new Api exception.
   *
   * @param message   the message
   * @param errorCode the error code
   */
  public ApiException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

}
