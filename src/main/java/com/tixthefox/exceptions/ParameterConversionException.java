package com.tixthefox.exceptions;

import org.springframework.http.HttpStatus;

public class ParameterConversionException extends BaseShipException {
  public ParameterConversionException(String message) {super(message);}
  public ParameterConversionException(String message, HttpStatus status) {
    super(message, status);
  }
}
