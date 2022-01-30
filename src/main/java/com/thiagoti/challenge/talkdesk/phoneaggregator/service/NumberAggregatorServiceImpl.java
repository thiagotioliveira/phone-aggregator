package com.thiagoti.challenge.talkdesk.phoneaggregator.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.thiagoti.challenge.talkdesk.phoneaggregator.service.exception.NoValidNumberFoundException;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.Filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class NumberAggregatorServiceImpl implements NumberAggregatorService {

  private final Filter numberAggregatorFilter;

  private final SectorService sectorService;

  private final PrefixService prefixService;

  @Override
  public Map<String, Map<String, Integer>> countGroupBySectorAndPrefix(Collection<String> numbers) throws Exception {
    
    Set<String> numbersFiltered = numberAggregatorFilter.doFilter(numbers);
    log.debug("numbersFiltered = {}", numbersFiltered);

    if (Boolean.TRUE.equals(numbersFiltered.isEmpty())) {
      throw new NoValidNumberFoundException();
    }

    CompletableFuture<Map<String, Set<String>>> prefixNumbersMapCompletableFuture = prefixService.groupByAsync(numbersFiltered);

    CompletableFuture<Map<String, String>> numberSectorMapCompletableFuture = sectorService.getByNumbersAsync(numbersFiltered);

    CompletableFuture.allOf(prefixNumbersMapCompletableFuture, numberSectorMapCompletableFuture).join();

    log.debug("start aggregation.. ");

    return process(numberSectorMapCompletableFuture.get(), prefixNumbersMapCompletableFuture.get());
  }

  private Map<String, Map<String, Integer>> process(final Map<String, String> numberSectorMap, Map<String, Set<String>> prefixNumbersMap) {
    log.debug("numberSectorMap = {}", numberSectorMap);
    log.debug("prefixNumbersMap = {}", prefixNumbersMap);

    final Map<String, Map<String, Integer>> aggregateMap = new TreeMap<>();

    prefixNumbersMap.entrySet().forEach(e -> {
      e.getValue().forEach(n -> {
        Map<String, Integer> sectorCountMap = aggregateMap.get(e.getKey());

        if (Objects.isNull(sectorCountMap)) {
          sectorCountMap = new HashMap<>();
          aggregateMap.put(e.getKey(), sectorCountMap);
        }
        
        if(numberSectorMap.containsKey(n)) {
          String sectorName = numberSectorMap.get(n);
          
          if (Boolean.FALSE.equals(sectorCountMap.containsKey(sectorName))) {
            sectorCountMap.put(sectorName, 0);
          }
          sectorCountMap.put(sectorName, sectorCountMap.get(sectorName) + 1);
        }
      });
    });
    return aggregateMap;
  }

}
