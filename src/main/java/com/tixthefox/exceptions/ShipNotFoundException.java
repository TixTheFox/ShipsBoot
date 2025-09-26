package com.tixthefox.exceptions;

import org.springframework.http.HttpStatus;

public class ShipNotFoundException extends BaseShipException {
  public ShipNotFoundException(String message) {super(message);}
  public ShipNotFoundException(String message, HttpStatus status) {
    super(message, status);
  }
}
