package com.thiagoti.challenge.talkdesk.phoneaggregator.repository;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface PrefixRepository {

  Map<String, Set<String>> groupBy(Collection<String> numbers);

}
