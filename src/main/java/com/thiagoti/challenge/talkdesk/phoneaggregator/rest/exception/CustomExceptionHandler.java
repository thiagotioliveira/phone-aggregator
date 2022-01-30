package com.thiagoti.challenge.talkdesk.phoneaggregator.rest.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.thiagoti.challenge.talkdesk.phoneaggregator.service.exception.IntegrationException;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.exception.NoValidNumberFoundException;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.exception.PrefixFileException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler {

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @ExceptionHandler(NoValidNumberFoundException.class)
  @ResponseBody
  public ResponseEntity<Void> handleNoValidNumberFoundException(NoValidNumberFoundException e) {
    log.debug("handleNoValidNumberFoundException {}", e.getMessage());
    return new ResponseEntity(new CustomError(e.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @ExceptionHandler(PrefixFileException.class)
  @ResponseBody
  public ResponseEntity<Void> handlePrefixFileException(PrefixFileException e) {
    log.debug("handlePrefixFileException {}", e.getMessage());
    return new ResponseEntity(new CustomError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @ExceptionHandler(IntegrationException.class)
  @ResponseBody
  public ResponseEntity<Void> handleIntegrationException(IntegrationException e) {
    log.debug("handleIntegrationException {}", e.getMessage());
    return new ResponseEntity(new CustomError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
