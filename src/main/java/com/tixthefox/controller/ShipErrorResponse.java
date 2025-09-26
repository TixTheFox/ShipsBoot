package com.tixthefox.controller;

public class ShipErrorResponse {
  private int status;
  private String Message;

  public ShipErrorResponse() {
  }

  public ShipErrorResponse(int status, String message) {
    this.status = status;
    Message = message;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return Message;
  }

  public void setMessage(String message) {
    Message = message;
  }
}
