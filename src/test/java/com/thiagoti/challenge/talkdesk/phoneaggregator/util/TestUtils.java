package com.thiagoti.challenge.talkdesk.phoneaggregator.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.Filter;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.simple.RemoveEmptyCharRegexFilter;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.simple.RemoveInvalidNumberRegexFilter;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.filter.simple.RemoveRepeatedNumberFilter;
import com.thiagoti.challenge.talkdesk.phoneaggregator.service.mapper.PhoneNumberMapperImpl;

public class TestUtils {

  public static final String MOCK_TEXT = "this is a simple mock text";

  public static final String END_POINT_SECTOR_INTEGRATION = "/sector/%s";

  public static String getNumber() {
    return "+1983248";
  }

  public static String getPath() {
    return "/static/prefixes.txt";
  }

  public static Collection<String> getNumbersList() {
    return List.of("+1983248", "001382355", "+147 8192", "+4439877");
  }

  public static Collection<String> getCleanNumbersList() {
    return List.of("1983248", "1382355", "147 8192", "4439877");
  }

  public static Collection<String> getInvalidNumbersList() {
    return List.of("+ 35191734022", "00 1382355", "+023", "+147 8192 344444");
  }

  public static Collection<String> getReservedChars() {
    return List.of("\\+", "00");
  }

  public static String getRegex() {
    return "(\\+|00)(([1-9])(\\s*\\d)(\\s*\\d)|([1-9])(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)|([1-9])(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)|([1-9])(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)|([1-9])(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)|([1-9])(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)|([1-9])(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d)(\\s*\\d))$";
  }

  public static List<Filter> getNumberAggregatorFilters() {
    return Collections.unmodifiableList(List.of(new RemoveInvalidNumberRegexFilter(Pattern.compile(getRegex())), new RemoveEmptyCharRegexFilter(),
        new RemoveRepeatedNumberFilter(new PhoneNumberMapperImpl(getReservedChars()))));
  }

  public static String readValueFromFile(String pathToFile) {
    try {
      return IOUtils.resourceToString(pathToFile, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException("Failed to read resource file");
    }
  }

}
