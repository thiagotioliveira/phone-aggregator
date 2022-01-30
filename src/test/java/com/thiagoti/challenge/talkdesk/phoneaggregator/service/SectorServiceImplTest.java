package com.thiagoti.challenge.talkdesk.phoneaggregator.service;

import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.MOCK_TEXT;
import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.getNumbersList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thiagoti.challenge.talkdesk.phoneaggregator.integration.SectorClient;

public class SectorServiceImplTest {

  private SectorClient sectorClient;

  @BeforeEach
  void init() {
    this.sectorClient = mock(SectorClient.class);
  }

  @Test
  void getByNumbersTest() {
    when(sectorClient.getByNumber(any())).thenReturn(MOCK_TEXT);

    final Collection<String> numbersList = getNumbersList();
    Map<String, String> resultMap = new SectorServiceImpl(sectorClient).getByNumbers(numbersList);
    numbersList.forEach(n -> assertEquals(MOCK_TEXT, resultMap.get(n)));
  }

}
