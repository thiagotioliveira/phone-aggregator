package com.thiagoti.challenge.talkdesk.phoneaggregator.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thiagoti.challenge.talkdesk.phoneaggregator.service.mapper.PhoneNumberMapper;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.mapper.PhoneNumberMapperImpl;

@Configuration
public class MapperConfig {

  @Bean
  public PhoneNumberMapper phoneNumberMapper(@Value("${app.number.reservedChars}") List<String> reservedChars) {
    return new PhoneNumberMapperImpl(reservedChars);
  }

}
