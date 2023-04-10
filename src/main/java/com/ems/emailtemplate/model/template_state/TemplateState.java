package com.ems.emailtemplate.model.template_state;

import com.ems.responsegenerator.ResponseGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;

public abstract class TemplateState {
    protected Object data;
    protected HttpStatus status;

    public TemplateState(Object data) {
        this.data = data;
    }

    public TemplateState(HttpStatus status, Object data) {
        this.status = status;
        this.data = data;
    }

    public abstract ResponseGenerator<JsonNode> getResponse();

    public Object getData() {
        return data;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
