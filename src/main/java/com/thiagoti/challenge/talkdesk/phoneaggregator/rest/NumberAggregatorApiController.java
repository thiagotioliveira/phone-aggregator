package com.thiagoti.challenge.talkdesk.phoneaggregator.rest;

import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.EndpointsConstants.AGGREGATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thiagoti.challenge.talkdesk.phoneaggregator.service.NumberAggregatorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = AGGREGATE, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class NumberAggregatorApiController {

  private final NumberAggregatorService service;

  @PostMapping
  @ResponseStatus(code = HttpStatus.OK)
  public Map<String, Map<String, Integer>> postAggregate(@RequestBody List<String> numbers) throws Exception {
    log.info("request for aggregate.. {}", numbers);
    final Map<String, Map<String, Integer>> aggregate = service.countGroupBySectorAndPrefix(numbers);
    log.info("response body.. {}", aggregate);
    return aggregate;
  }

}
