package com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.simple;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.Filter;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.mapper.PhoneNumberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class RemoveRepeatedNumberFilter implements Filter {

  private final PhoneNumberMapper phoneNumberMapper;

  @Override
  public Set<String> doFilter(Collection<String> numbers) {
    log.debug("removing repeated numbers..");
    return numbers.stream().map(n -> phoneNumberMapper.toCleanNumber(n)).collect(Collectors.toSet());
  }

}
