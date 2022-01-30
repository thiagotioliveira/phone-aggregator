package com.thiagoti.challenge.talkdesk.phoneaggregator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.thiagoti.challenge.talkdesk.phoneaggregator.integration.SectorClient;
import com.thiagoti.challenge.talkdesk.phoneaggregator.integration.http.SectorHttpClient;

@Configuration
public class SectorHttpClientConfig {

  @Bean
  public SectorClient sectorClient(RestTemplate restTemplate, @Value("${app.integration.sector.getByNumber.url}") String url) {
    return new SectorHttpClient(restTemplate, url);
  }

}
