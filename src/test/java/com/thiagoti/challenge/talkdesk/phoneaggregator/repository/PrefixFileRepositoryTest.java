package com.thiagoti.challenge.talkdesk.phoneaggregator.repository;

import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.getCleanNumbersList;
import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.getPath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class PrefixFileRepositoryTest {

  @Test
  void groupByTest() throws Exception {
    Map<String, Set<String>> groupBy = new PrefixFileRepository(getPath())
        .groupBy(getCleanNumbersList());
    assertTrue(groupBy.containsKey("44"));
    assertEquals(1, groupBy.get("44").size());
    assertTrue(groupBy.containsKey("1"));
    assertEquals(3, groupBy.get("1").size());
  }

}
