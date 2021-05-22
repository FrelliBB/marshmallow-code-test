package com.marshmallow.hiring.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    // ⚠️ Warning ⚠️
    // This test is here to ensure you have exposed an endpoint on the right path for our automated tests running once you submit.
    // Please do not change this file. If you do change it, Codescreen will prevent you from submitting your solution.
    // You can add your own tests in new files, only this file is protected by Codescreen.

    private static final MockHttpServletRequestBuilder REQUEST_BUILDER =
        request(HttpMethod.POST, "/instructions")
            .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .accept(APPLICATION_JSON);

    @Autowired
    private MockMvc mockMvc;

    @Test
    void ensuresCorrectEndpointIsExposed() throws Exception {
        mockMvc.perform(REQUEST_BUILDER.content(BASIC_CASE_REQUEST))
            .andExpect(status().isOk());
    }

    private static final String BASIC_CASE_REQUEST =
        "{\"areaSize\": [5, 5], \"startingPosition\": [1, 2], \"oilPatches\": [[1, 0], [2, 2], [2, 3]], \"navigationInstructions\": \"NNESEESWNWW\"}";

}