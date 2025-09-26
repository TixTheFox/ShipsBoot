package com.tixthefox.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidIdException extends BaseShipException {
  public InvalidIdException(String message) {super(message);}
  public InvalidIdException(String message, HttpStatus status) {
    super(message, status);
  }
}
