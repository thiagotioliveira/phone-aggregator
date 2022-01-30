package com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.simple;

import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.getInvalidNumbersList;
import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.getNumbersList;
import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.getRegex;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Set;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RemoveInvalidNumberRegexFilterTest {

  private static Pattern pattern;

  @BeforeAll
  static void init() {
    pattern = Pattern.compile(getRegex());
  }

  @Test
  void doFilterByValidNumbersTest() {
    final Collection<String> numbersList = getNumbersList();
    Set<String> numbersFiltered = new RemoveInvalidNumberRegexFilter(pattern).doFilter(numbersList);
    assertTrue(numbersFiltered.size() == numbersList.size());
  }

  @Test
  void doFilterByInvalidNumbersTest() {
    final Collection<String> numbersList = getInvalidNumbersList();
    Set<String> numbersFiltered = new RemoveInvalidNumberRegexFilter(pattern).doFilter(numbersList);
    assertTrue(numbersFiltered.isEmpty());
  }

}
