package com.thiagoti.challenge.talkdesk.phoneaggregator.repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import com.thiagoti.challenge.talkdesk.phoneaggregator.service.exception.PrefixFileException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PrefixFileRepository implements PrefixRepository {

  private final String path;

  /**
   * Returns from numbers grouped by prefixes
   */
  @Override
  public Map<String, Set<String>> groupBy(Collection<String> numbers) {

    final Map<String, Set<String>> numbersGroupByPrefixes = new ConcurrentHashMap<>();

    log.debug("open stream from file {}", this.path);
    try (Stream<String> lines = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(this.path))).lines().parallel()) {
      lines.forEach(prefix ->
      numbers.stream().forEach(n -> {
        if (n.startsWith(prefix)) {
          Set<String> numbersGroup = numbersGroupByPrefixes.get(prefix);
          if (Objects.isNull(numbersGroup)) {
            numbersGroup = Collections.synchronizedSet(new HashSet<>());
            numbersGroupByPrefixes.put(prefix, numbersGroup);
          }
          numbersGroup.add(n);
        }
      }));
    } catch (Exception e) {
      log.error("error - problem trying to get prefixes", e);
      throw new PrefixFileException(e);
    }

    log.debug("close stream, return {}", numbersGroupByPrefixes);
    return numbersGroupByPrefixes;
  }

}
