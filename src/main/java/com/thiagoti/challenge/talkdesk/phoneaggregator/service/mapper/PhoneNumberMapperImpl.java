package com.thiagoti.challenge.talkdesk.phoneaggregator.service.mapper;

import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.Constants.BACKSLASH;

import java.util.Collection;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PhoneNumberMapperImpl implements PhoneNumberMapper {

  private final Collection<String> reservedChars;

  @Override
  public String toCleanNumber(String number) {
    Optional<String> reservedCharOptional = reservedChars.stream()
        .filter(reservedChar -> number.startsWith(reservedChar.replace(BACKSLASH, Strings.EMPTY)))
        .findFirst();
    return reservedCharOptional.isPresent() ? number.replaceFirst(reservedCharOptional.get(), Strings.EMPTY) : number;
  }

}
