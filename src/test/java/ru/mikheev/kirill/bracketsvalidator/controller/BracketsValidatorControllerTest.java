package ru.mikheev.kirill.bracketsvalidator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.mikheev.kirill.bracketsvalidator.BracketsValidatorApplication;
import ru.mikheev.kirill.bracketsvalidator.controller.dto.BracketsValidationInTextRequest;
import ru.mikheev.kirill.bracketsvalidator.exception.TextProcessingException;
import ru.mikheev.kirill.bracketsvalidator.service.contract.BracketsValidationOperations;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BracketsValidatorApplication.class
)
@AutoConfigureMockMvc
@ActiveProfiles("rest-test")
class BracketsValidatorControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String URI = "/checkBrackets";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BracketsValidationOperations bracketsValidationOperations;

    @DisplayName("Test rest controller with error during text processing")
    @Test
    void testRestTextProcessingException() throws Exception {
        var requestWithException = new BracketsValidationInTextRequest();
        requestWithException.setText("Text processing exception");
        Mockito
                .when(bracketsValidationOperations.validateBracketsSequenceWithText(requestWithException.getText()))
                .thenThrow(new TextProcessingException(new RuntimeException("text exception")));
        mvc.perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(requestWithException))
                ).andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Error during text processing"));
    }

    @DisplayName("Test rest controller with unexpected error during application run")
    @Test
    void testRestSimpleRuntimeException() throws Exception {
        var requestWithException = new BracketsValidationInTextRequest();
        requestWithException.setText("Runtime exception");
        Mockito
                .when(bracketsValidationOperations.validateBracketsSequenceWithText(requestWithException.getText()))
                .thenThrow(new RuntimeException("text exception"));
        mvc.perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(requestWithException))
                ).andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Unknown error while request processing"));
    }

    @DisplayName("Test rest controller OK result")
    @Test
    void testRestCorrectResult() throws Exception {
        var correctRequest = new BracketsValidationInTextRequest();
        correctRequest.setText("(a)");
        Mockito
                .when(bracketsValidationOperations.validateBracketsSequenceWithText(correctRequest.getText()))
                .thenReturn(true);
        mvc.perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(correctRequest))
                ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.isCorrect", is(true)));
    }
}