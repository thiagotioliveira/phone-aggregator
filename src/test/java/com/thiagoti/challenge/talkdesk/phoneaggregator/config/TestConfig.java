package com.thiagoti.challenge.talkdesk.phoneaggregator.config;

import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.END_POINT_SECTOR_INTEGRATION;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import com.thiagoti.challenge.talkdesk.phoneaggregator.integration.SectorClient;
import com.thiagoti.challenge.talkdesk.phoneaggregator.integration.http.SectorHttpClient;

@Configuration
public class TestConfig {

  @Bean
  @Primary
  public SectorClient sectorClient(RestTemplate restTemplate, @Value("${wiremock.server.port}") int wiremockPort) {
    return new SectorHttpClient(restTemplate, "http://localhost:" + wiremockPort + END_POINT_SECTOR_INTEGRATION);
  }
}
