package com.marshmallow.hiring.web;

import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static java.lang.ClassLoader.getSystemResource;
import static java.lang.String.format;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
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

    @ParameterizedTest
    @ValueSource(strings = {"oilPatchesEverywhere", "providedExample"})
    void instructionExamples_successful(String example) throws Exception {
        String request = readFile(example + "/request.json");
        String response = readFile(example + "/response.json");

        mockMvc.perform(REQUEST_BUILDER.content(request))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalidVectorRequest",
            "invalidNavigationInstruction",
            "invalidSeaAreSize",
            "invalidNavigationPath",
            "invalidOilPatchLocation"
    })
    void invalidRequests_returns400BadRequest(String file) throws Exception {
        String request = readFile(format("invalidRequests/%s.json", file));
        mockMvc.perform(REQUEST_BUILDER.content(request))
                .andExpect(status().isBadRequest());
    }
}
