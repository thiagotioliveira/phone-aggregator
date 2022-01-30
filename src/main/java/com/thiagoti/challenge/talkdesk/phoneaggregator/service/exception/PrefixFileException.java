package com.thiagoti.challenge.talkdesk.phoneaggregator.service.exception;

import lombok.Getter;

@Getter
public class PrefixFileException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public PrefixFileException(Exception e) {
    super("problem trying to get prefixes", e);
  }

}
