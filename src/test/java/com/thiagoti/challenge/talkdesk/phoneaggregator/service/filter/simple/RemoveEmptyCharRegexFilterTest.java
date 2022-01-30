package com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.simple;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class RemoveEmptyCharRegexFilterTest {

  @Test
  void doFilterTest() {
    final Collection<String> numbersList = List.of("+1 2  3", "0033 2");
    Set<String> numbersFiltered = new RemoveEmptyCharRegexFilter().doFilter(numbersList);
    assertTrue(numbersFiltered.contains("+123"));
    assertTrue(numbersFiltered.contains("00332"));
  }

}
