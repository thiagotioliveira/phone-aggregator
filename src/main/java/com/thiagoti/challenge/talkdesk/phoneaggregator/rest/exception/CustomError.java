package com.thiagoti.challenge.talkdesk.phoneaggregator.rest.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomError implements Serializable {

  private static final long serialVersionUID = 1L;

  private String message;

}
