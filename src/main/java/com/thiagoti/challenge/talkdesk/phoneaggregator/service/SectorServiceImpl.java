package com.thiagoti.challenge.talkdesk.phoneaggregator.service;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.thiagoti.challenge.talkdesk.phoneaggregator.integration.SectorClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SectorServiceImpl implements SectorService {

  private final SectorClient sectorClient;

  @Override
  public Map<String, String> getByNumbers(Collection<String> numbers) {
    final Map<String, String> resultMap = new ConcurrentHashMap<>();
    log.debug("getByNumbers = {}", numbers);

    numbers.parallelStream().forEach(n -> {
      String sectorName = sectorClient.getByNumber(n);
      log.debug("number {} - sector {}", n, sectorName);
      if (Objects.nonNull(sectorName)) {
        resultMap.put(n, sectorName);
      }
    });

    return resultMap;
  }

  @Override
  @Async
  public CompletableFuture<Map<String, String>> getByNumbersAsync(Collection<String> numbers) {
    return CompletableFuture.completedFuture(getByNumbers(numbers));
  }

}
