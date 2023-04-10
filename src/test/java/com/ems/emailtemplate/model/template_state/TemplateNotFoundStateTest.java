package com.ems.emailtemplate.model.template_state;

import com.ems.responsegenerator.ResponseGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TemplateNotFoundStateTest {

    @Test
    public void getResponseSuccessTest() {
        TemplateState state = new TemplateNotFoundState(-1);
        ResponseGenerator<JsonNode> response = state.getResponse();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
        assertEquals(-1, response.getData());
    }

    @Test
    public void getResponseFailedTest() {
        TemplateState state = new TemplateNotFoundState(HttpStatus.BAD_REQUEST, "Template Not Available");
        ResponseGenerator<JsonNode> response = state.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
        assertEquals("Template Not Available", response.getData());
    }

    @Test
    public void getDataSuccessTest() {
        TemplateState templateState = new TemplateNotFoundState(12);
        Integer data = (Integer) templateState.getData();

        assertEquals(12, data);
    }

    @Test
    public void getDataFailureTest() {
        TemplateState templateState = new TemplateNotFoundState(12);
        Integer data = (Integer) templateState.getData();

        assertNotEquals(11, data);
    }

    @Test
    public void getStatusSuccessTest() {
        TemplateState templateState = new TemplateNotFoundState(12);
        HttpStatus status = templateState.getStatus();

        assertEquals(HttpStatus.NOT_FOUND, status);
    }

    @Test
    public void getStatusFailureTest() {
        TemplateState templateState = new TemplateNotFoundState(12);
        HttpStatus status = templateState.getStatus();

        assertNotEquals(HttpStatus.CREATED, status);
    }
}
