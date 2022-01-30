package com.thiagoti.challenge.talkdesk.phoneaggregator.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface SectorService {

  CompletableFuture<Map<String, String>> getByNumbersAsync(Collection<String> numbers);

  Map<String, String> getByNumbers(Collection<String> numbers);

}
