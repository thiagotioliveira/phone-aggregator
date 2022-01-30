package com.thiagoti.challenge.talkdesk.phoneaggregator.service.exception;

public class IntegrationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public IntegrationException(Throwable cause) {
    super("Unable to perform integration with sector API", cause);
  }

}
