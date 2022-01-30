package com.thiagoti.challenge.talkdesk.phoneaggregator.integration.http;

import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.Constants.NUMBER;
import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.Constants.SECTOR;
import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.MOCK_TEXT;
import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.getNumber;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class SectorHttpClientTest {

  private RestTemplate restTemplate;

  @BeforeEach
  void init() {
    this.restTemplate = mock(RestTemplate.class);
    when(restTemplate.getForObject(anyString(), any())).thenReturn(Map.of(NUMBER, getNumber(), SECTOR, MOCK_TEXT));
  }

  @Test
  void getByNumberTest() {
    String sectorName = new SectorHttpClient(restTemplate, MOCK_TEXT).getByNumber(getNumber());
    assertEquals(MOCK_TEXT, sectorName);
  }

}
