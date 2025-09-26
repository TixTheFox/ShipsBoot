package com.tixthefox.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidParametersException extends BaseShipException {
  public InvalidParametersException(String message) {super(message);}
  public InvalidParametersException(String message, HttpStatus status) {
    super(message, status);
  }
}
