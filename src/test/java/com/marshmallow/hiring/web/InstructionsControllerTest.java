package com.marshmallow.hiring.web;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static java.lang.ClassLoader.getSystemResource;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InstructionsControllerTest {

    private static final MockHttpServletRequestBuilder REQUEST_BUILDER =
            request(HttpMethod.POST, "/instructions")
                    .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                    .accept(APPLICATION_JSON);

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    private static String readFile(String name) {
        return new String(readAllBytes(get(getSystemResource(name).toURI())));
    }

    @Test
    void basicInstructionsExample_successful() throws Exception {
        String request = readFile("basicInstructionsExample/request.json");
        String response = readFile("basicInstructionsExample/response.json");

        mockMvc.perform(REQUEST_BUILDER.content(request))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }
}
