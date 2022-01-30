package com.thiagoti.challenge.talkdesk.phoneaggregator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thiagoti.challenge.talkdesk.phoneaggregator.repository.PrefixRepository;

public class PrefixServiceImplTest {

  private PrefixRepository prefixRepository;

  @BeforeEach
  void init() {
    this.prefixRepository = mock(PrefixRepository.class);
  }

  @Test
  void groupByTest() throws Exception {
    when(prefixRepository.groupBy(any())).thenReturn(Map.of("1", Set.of("1983248")));
    Map<String, Set<String>> resultMap = new PrefixServiceImpl(prefixRepository).groupBy(List.of("1983248"));
    assertTrue(resultMap.containsKey("1"));
    assertEquals(1, resultMap.get("1").size());
  }

}
