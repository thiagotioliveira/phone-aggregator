package com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter;

import java.util.Collection;
import java.util.Set;

public interface Filter {

  Set<String> doFilter(Collection<String> numbers);

}
