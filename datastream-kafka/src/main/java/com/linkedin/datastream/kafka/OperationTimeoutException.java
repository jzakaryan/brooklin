package com.linkedin.datastream.kafka;

public class OperationTimeoutException extends Exception {
  private static final long serialVersionUID = 1;

  public OperationTimeoutException(String message) {
    super(message);
  }
}
