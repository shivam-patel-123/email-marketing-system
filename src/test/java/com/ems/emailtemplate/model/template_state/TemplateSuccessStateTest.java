package com.ems.emailtemplate.model.template_state;

import com.ems.responsegenerator.ResponseGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TemplateSuccessStateTest {

    @Test
    public void getResponseSuccessTest() {
        TemplateState templateState = new TemplateSuccessState(12);
        ResponseGenerator<JsonNode> response = templateState.getResponse();

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(12, response.getData());
    }

    @Test
    public void getResponseCustomStatusTest() {
        TemplateState templateState = new TemplateSuccessState(HttpStatus.CREATED, 12);
        ResponseGenerator<JsonNode> response = templateState.getResponse();

        assertEquals(HttpStatus.CREATED, response.getStatus());
        assertEquals(12, response.getData());
    }

    @Test
    public void getDataSuccessTest() {
        TemplateState templateState = new TemplateSuccessState(12);
        Integer data = (Integer) templateState.getData();

        assertEquals(12, data);
    }

    @Test
    public void getDataFailureTest() {
        TemplateState templateState = new TemplateSuccessState(12);
        Integer data = (Integer) templateState.getData();

        assertNotEquals(11, data);
    }

    @Test
    public void getStatusSuccessTest() {
        TemplateState templateState = new TemplateSuccessState(12);
        HttpStatus status = templateState.getStatus();

        assertEquals(HttpStatus.OK, status);
    }

    @Test
    public void getStatusFailureTest() {
        TemplateState templateState = new TemplateSuccessState(12);
        HttpStatus status = templateState.getStatus();

        assertNotEquals(HttpStatus.CREATED, status);
    }
}
