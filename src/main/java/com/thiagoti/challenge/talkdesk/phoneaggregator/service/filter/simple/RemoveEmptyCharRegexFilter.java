package com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.simple;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;

import com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.Filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class RemoveEmptyCharRegexFilter implements Filter {

  private static final String REGEX = "\\s+";

  @Override
  public Set<String> doFilter(Collection<String> numbers) {
    log.debug("removing spaces numbers..");
    return numbers.stream().map(n -> n.replaceAll(REGEX, Strings.EMPTY)).collect(Collectors.toSet());
  }

}
