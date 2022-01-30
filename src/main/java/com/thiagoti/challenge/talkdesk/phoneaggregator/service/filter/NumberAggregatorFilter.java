package com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NumberAggregatorFilter implements Filter {

  private final List<Filter> filters;

  @Override
  public Set<String> doFilter(Collection<String> numbers) {
    for (Filter filter : filters) {
      Set<String> temp = filter.doFilter(numbers);
      numbers = temp;
    }

    return new HashSet<>(numbers);
  }
}
