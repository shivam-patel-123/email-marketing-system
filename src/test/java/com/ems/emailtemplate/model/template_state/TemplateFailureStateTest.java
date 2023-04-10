package com.ems.emailtemplate.model.template_state;

import com.ems.responsegenerator.ResponseGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TemplateFailureStateTest {

    @Test
    public void getResponseSuccessTest() {
        TemplateState state = new TemplateFailureState("SERVER ERROR");
        ResponseGenerator<JsonNode> response = state.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
        assertEquals("SERVER ERROR", response.getData());
    }

    @Test
    public void getResponseCustomStatusTest() {
        TemplateState state = new TemplateFailureState(HttpStatus.NOT_IMPLEMENTED, "SERVER ERROR");
        ResponseGenerator<JsonNode> response = state.getResponse();

        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatus());
        assertEquals("SERVER ERROR", response.getData());
    }

    @Test
    public void getDataSuccessTest() {
        TemplateState templateState = new TemplateFailureState(12);
        Integer data = (Integer) templateState.getData();

        assertEquals(12, data);
    }

    @Test
    public void getDataFailureTest() {
        TemplateState templateState = new TemplateFailureState(12);
        Integer data = (Integer) templateState.getData();

        assertNotEquals(11, data);
    }

    @Test
    public void getStatusSuccessTest() {
        TemplateState templateState = new TemplateFailureState(12);
        HttpStatus status = templateState.getStatus();

        assertEquals(HttpStatus.BAD_REQUEST, status);
    }

    @Test
    public void getStatusFailureTest() {
        TemplateState templateState = new TemplateFailureState(12);
        HttpStatus status = templateState.getStatus();

        assertNotEquals(HttpStatus.CREATED, status);
    }
}
