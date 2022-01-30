package com.thiagoti.challenge.talkdesk.phoneaggregator.service;

import java.util.Collection;
import java.util.Map;

public interface NumberAggregatorService {

  /**
   * count numbers group by sector and prefix
   * 
   * @param numbers
   * @return
   * @throws Exception
   */
  Map<String, Map<String, Integer>> countGroupBySectorAndPrefix(Collection<String> numbers) throws Exception;

}
