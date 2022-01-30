package com.thiagoti.challenge.talkdesk.phoneaggregator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thiagoti.challenge.talkdesk.phoneaggregator.repository.PrefixFileRepository;

@Configuration
public class PrefixRepositoryConfig {

  @Bean
  public PrefixFileRepository prefixFileRepository(@Value("${app.repository.file.path}") String path) {
    return new PrefixFileRepository(path);
  }

}
