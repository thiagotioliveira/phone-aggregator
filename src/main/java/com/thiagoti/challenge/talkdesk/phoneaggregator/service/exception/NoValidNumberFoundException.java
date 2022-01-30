package com.thiagoti.challenge.talkdesk.phoneaggregator.service.exception;

import lombok.Getter;

@Getter
public class NoValidNumberFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public NoValidNumberFoundException() {
    super("no valid number found");
  }
  
}
