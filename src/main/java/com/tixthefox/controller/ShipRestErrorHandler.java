package com.tixthefox.controller;

import com.tixthefox.exceptions.BaseShipException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShipRestErrorHandler {

  @ExceptionHandler
  public ResponseEntity<ShipErrorResponse> ShipExceptionHandler(BaseShipException exc) {
    ShipErrorResponse error = new ShipErrorResponse();

    error.setStatus(exc.getStatus().value());
    error.setMessage(exc.getMessage());

    return new ResponseEntity<>(error, exc.getStatus());
  }

  @ExceptionHandler
  public ResponseEntity<ShipErrorResponse> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exc) {
    ShipErrorResponse error = new ShipErrorResponse();

    error.setStatus(HttpStatus.BAD_REQUEST.value());
    error.setMessage("Некорректное тело запроса: " + exc.getMessage());

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
