package com.thiagoti.challenge.talkdesk.phoneaggregator.service;

import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.getInvalidNumbersList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thiagoti.challenge.talkdesk.phoneaggregator.service.exception.NoValidNumberFoundException;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.Filter;

public class NumberAggregatorServiceImplTest {

  private Filter numberAggregatorFilter;

  private SectorService sectorService;

  private PrefixService prefixService;

  @BeforeEach
  void init() throws Exception {
    this.sectorService = mock(SectorService.class);
    this.prefixService = mock(PrefixService.class);
    this.numberAggregatorFilter = mock(Filter.class);
  }

  @Test
  void countGroupBySectorAndPrefixSuccess() throws Exception {
    final String input1 = "+1983248";
    final String input2 = "001382355";
    final String input3 = "+147 8192";
    final String input4 = "+4439877";

    final String inputAfterFilter1 = "1983248";
    final String inputAfterFilter2 = "1382355";
    final String inputAfterFilter3 = "1478192";
    final String inputAfterFilter4 = "4439877";

    when(sectorService.getByNumbersAsync(any()))
        .thenReturn(CompletableFuture.completedFuture(Map.of(input1, "A", input2, "B", input3, "C", input4, "D")));
    when(prefixService.groupByAsync(any()))
        .thenReturn(CompletableFuture.completedFuture(Map.of("1", Set.of(input1, input2, input3), "44", Set.of(input4))));
    when(numberAggregatorFilter.doFilter(any())).thenReturn(Set.of(inputAfterFilter1, inputAfterFilter2, inputAfterFilter3, inputAfterFilter4));

    Map<String, Map<String, Integer>> aggregateMap = new NumberAggregatorServiceImpl(numberAggregatorFilter, sectorService, prefixService)
        .countGroupBySectorAndPrefix(List.of(input1, input2, input3, input4));
    assertTrue(aggregateMap.containsKey("1"));
    assertTrue(aggregateMap.get("1").containsKey("A"));
    assertTrue(aggregateMap.get("1").containsKey("B"));
    assertTrue(aggregateMap.get("1").containsKey("C"));
    assertTrue(aggregateMap.containsKey("44"));
    assertTrue(aggregateMap.get("44").containsKey("D"));
  }

  @Test
  void countGroupBySectorAndPrefixNoValidNumberFoundExceptionTest() throws Exception {
    when(numberAggregatorFilter.doFilter(any())).thenReturn(new HashSet<>());
    try {
      new NumberAggregatorServiceImpl(numberAggregatorFilter, sectorService, prefixService).countGroupBySectorAndPrefix(getInvalidNumbersList());
      Assertions.fail();
    } catch (Exception e) {
      assertTrue(e instanceof NoValidNumberFoundException);
    }

  }

}
