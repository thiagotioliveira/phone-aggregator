package com.thiagoti.challenge.talkdesk.phoneaggregator.service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface PrefixService {

  CompletableFuture<Map<String, Set<String>>> groupByAsync(Collection<String> numbers);

  Map<String, Set<String>> groupBy(Collection<String> numbers) throws Exception;

}
