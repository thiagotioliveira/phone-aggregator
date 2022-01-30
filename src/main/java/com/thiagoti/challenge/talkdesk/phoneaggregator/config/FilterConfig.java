package com.thiagoti.challenge.talkdesk.phoneaggregator.config;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.Filter;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.NumberAggregatorFilter;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.simple.RemoveEmptyCharRegexFilter;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.simple.RemoveInvalidNumberRegexFilter;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.simple.RemoveRepeatedNumberFilter;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.mapper.PhoneNumberMapper;

@Configuration
public class FilterConfig {

  @Bean
  public Filter removeInvalidNumberRegexFilter(@Value("${app.number.regex}") String regex) {
    return new RemoveInvalidNumberRegexFilter(Pattern.compile(regex));
  }

  @Bean
  public Filter removeEmptyCharRegexFilter() {
    return new RemoveEmptyCharRegexFilter();
  }

  @Bean
  public Filter removeRepeatedNumberFilter(PhoneNumberMapper phoneNumberMapper) {
    return new RemoveRepeatedNumberFilter(phoneNumberMapper);
  }

  @Bean
  public Filter numberAggregatorFilter(Filter removeEmptyCharRegexFilter, Filter removeRepeatedNumberFilter, Filter removeInvalidNumberRegexFilter) {
    return new NumberAggregatorFilter(
        Collections.unmodifiableList(List.of(removeInvalidNumberRegexFilter, removeEmptyCharRegexFilter, removeRepeatedNumberFilter)));
  }
}
