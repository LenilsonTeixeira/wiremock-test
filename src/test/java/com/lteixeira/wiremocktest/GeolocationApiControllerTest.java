package com.lteixeira.wiremocktest;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import com.lteixeira.wiremocktest.controller.GeolocationResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GeolocationApiControllerTest extends BaseTest {

    @Test
    void test() throws Exception {

        final StubMapping stubMapping = WireMock.get(WireMock.urlPathMatching("/v3/81d77d34-f29d-4bb3-93de-6d30e557beb6"))
                .willReturn(response("ipapi-response.json", HttpStatus.OK.value())).build();

        this.wireMockServer.addStubMapping(stubMapping);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/geolocation")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        GeolocationResponse geolocationResponse = mapper.readValue(mvcResult.getResponse().getContentAsString(), GeolocationResponse.class);

        Assertions.assertEquals("success", geolocationResponse.getStatus());
        Assertions.assertEquals("Brazil", geolocationResponse.getCountry());
        Assertions.assertEquals("Campinas", geolocationResponse.getCity());
        Assertions.assertEquals("24.48.0.1", geolocationResponse.getIp());
    }
}
