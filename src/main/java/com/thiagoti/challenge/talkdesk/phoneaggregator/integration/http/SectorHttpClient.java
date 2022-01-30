package com.thiagoti.challenge.talkdesk.phoneaggregator.integration.http;

import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.Constants.SECTOR;

import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.thiagoti.challenge.talkdesk.phoneaggregator.integration.SectorClient;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.exception.IntegrationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class SectorHttpClient implements SectorClient {

  private final RestTemplate restTemplate;

  private final String getByNumberUrl;

  @Override
  public String getByNumber(String number) {
    try {
      return (String) this.restTemplate.getForObject(String.format(getByNumberUrl, number), Map.class).get(SECTOR);
    } catch (Exception e) {
      log.error("error - problem trying to get sectors", e);
      throw new IntegrationException(e);
    }
  }

}
