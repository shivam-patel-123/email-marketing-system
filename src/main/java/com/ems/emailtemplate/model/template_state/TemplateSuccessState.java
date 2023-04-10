package com.ems.emailtemplate.model.template_state;

import com.ems.responsegenerator.IResponseGeneratorFactory;
import com.ems.responsegenerator.JsonResponseGeneratorFactory;
import com.ems.responsegenerator.ResponseGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;

public class TemplateSuccessState extends TemplateState {

    public TemplateSuccessState(Object data) {
        super(data);
        this.status = HttpStatus.OK;
    }

    public TemplateSuccessState(HttpStatus status, Object data) {
        super(status, data);
    }

    @Override
    public ResponseGenerator<JsonNode> getResponse() {
        IResponseGeneratorFactory generatorFactory = new JsonResponseGeneratorFactory();
        return generatorFactory.createResponseGenerator(status, data);
    }
}
