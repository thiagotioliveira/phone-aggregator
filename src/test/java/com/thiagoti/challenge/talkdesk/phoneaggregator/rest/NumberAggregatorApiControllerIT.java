package com.thiagoti.challenge.talkdesk.phoneaggregator.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.EndpointsConstants.AGGREGATE;
import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.END_POINT_SECTOR_INTEGRATION;
import static com.thiagoti.challenge.talkdesk.phoneaggregator.util.TestUtils.readValueFromFile;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.thiagoti.challenge.talkdesk.phoneaggregator.PhoneAggregatorApplication;
import com.thiagoti.challenge.talkdesk.phoneaggregator.config.TestConfig;

@ActiveProfiles("it")
@SpringBootTest(classes = PhoneAggregatorApplication.class)
@AutoConfigureMockMvc
@AutoConfigureWireMock(httpsPort = 0, port = 0)
@ContextConfiguration(classes = TestConfig.class)
public class NumberAggregatorApiControllerIT {

  @Autowired
  private MockMvc mvc;

  @Test
  void postAggregateTest() throws Exception {
    stubFor(get(urlEqualTo(String.format(END_POINT_SECTOR_INTEGRATION, "1983248")))
        .willReturn(okJson(readValueFromFile("/json/response/get-sector-technology-response-body.json"))));
    stubFor(get(urlEqualTo(String.format(END_POINT_SECTOR_INTEGRATION, "1382355")))
        .willReturn(okJson(readValueFromFile("/json/response/get-sector-technology-response-body.json"))));
    stubFor(get(urlEqualTo(String.format(END_POINT_SECTOR_INTEGRATION, "1478192")))
        .willReturn(okJson(readValueFromFile("/json/response/get-sector-clothing-response-body.json"))));
    stubFor(get(urlEqualTo(String.format(END_POINT_SECTOR_INTEGRATION, "4439877")))
        .willReturn(okJson(readValueFromFile("/json/response/get-sector-banking-response-body.json"))));

  //@formatter:off
    MvcResult returnValue = mvc.perform(MockMvcRequestBuilders
        .post(AGGREGATE)
        .contentType(MediaType.APPLICATION_JSON)
        .content(readValueFromFile("/json/request/post-aggregate-request-body.json"))
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.1").exists())
        .andExpect(jsonPath("$.1.Technology").exists())
        .andExpect(jsonPath("$.1.Technology").value(2))
        .andExpect(jsonPath("$.1.Clothing").exists())
        .andExpect(jsonPath("$.1.Clothing").value(1))
        .andExpect(jsonPath("$.44").exists())
        .andExpect(jsonPath("$.44.Banking").exists())
        .andExpect(jsonPath("$.44.Banking").value(1))
        .andReturn();
    //@formatter:on
  }

}
