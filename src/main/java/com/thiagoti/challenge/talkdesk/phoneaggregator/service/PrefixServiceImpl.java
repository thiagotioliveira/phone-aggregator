package com.thiagoti.challenge.talkdesk.phoneaggregator.service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.thiagoti.challenge.talkdesk.phoneaggregator.repository.PrefixRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrefixServiceImpl implements PrefixService {

  private final PrefixRepository repository;

  @Override
  @Async
  public CompletableFuture<Map<String, Set<String>>> groupByAsync(Collection<String> numbers) {
    return CompletableFuture.completedFuture(groupBy(numbers));
  }

  @Override
  public Map<String, Set<String>> groupBy(Collection<String> numbers) {
    log.debug("groupBy - numbers = {}", numbers);
    Map<String, Set<String>> numberGroupByPrefixes = repository.groupBy(numbers);
    log.debug("numberGroupByPrefixes = {}", numberGroupByPrefixes);
    return numberGroupByPrefixes;
  }

}
