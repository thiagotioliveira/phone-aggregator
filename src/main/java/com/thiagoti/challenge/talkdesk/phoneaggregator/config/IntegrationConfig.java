package com.thiagoti.challenge.talkdesk.phoneaggregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IntegrationConfig {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

}
