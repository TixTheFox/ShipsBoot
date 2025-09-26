package com.tixthefox.exceptions;

import org.springframework.http.HttpStatus;

public class BaseShipException extends RuntimeException {
  private HttpStatus status;

  public BaseShipException(String message) {
    super(message);
    this.status = HttpStatus.BAD_REQUEST;
  }

  public BaseShipException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }
}
