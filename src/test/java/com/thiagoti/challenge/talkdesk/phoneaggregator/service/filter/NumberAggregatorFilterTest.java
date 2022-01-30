package com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter;

import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.getNumberAggregatorFilters;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class NumberAggregatorFilterTest {

  @Test
  void doFilterTest() {
    Set<String> numbersFiltered = new NumberAggregatorFilter(getNumberAggregatorFilters())
            .doFilter(List.of("+1 23", "00123"));
    assertEquals(1, numbersFiltered.size());
  }

}
