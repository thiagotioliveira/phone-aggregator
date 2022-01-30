package com.thiagoti.challenge.talkdesk.phoneaggregator.service.mapper;

import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.getNumber;
import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.getReservedChars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PhoneNumberMapperImplTest {

  @Test
  void checkMappingSuccess() {
    String number = getNumber();
    String cleanNumber = new PhoneNumberMapperImpl(getReservedChars()).toCleanNumber(number);
    getReservedChars().forEach(c -> {
      if (cleanNumber.contains(c)) {
        Assertions.fail();
      }
    });
  }

}
