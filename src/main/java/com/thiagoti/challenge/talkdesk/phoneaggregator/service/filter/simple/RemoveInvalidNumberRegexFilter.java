package com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.simple;

import java.util.Collection;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.Filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class RemoveInvalidNumberRegexFilter implements Filter {

  private final Pattern pattern;
  
  @Override
  public Set<String> doFilter(Collection<String> numbers) {
    log.debug("removing invalid numbers..");
    return numbers.stream().filter(pattern.asPredicate()).collect(Collectors.toSet());
  }

}
