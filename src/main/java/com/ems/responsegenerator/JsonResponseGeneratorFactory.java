package com.ems.responsegenerator;

import org.springframework.http.HttpStatus;

public class JsonResponseGeneratorFactory implements IResponseGeneratorFactory {
    @Override
    public ResponseGenerator createResponseGenerator(HttpStatus status, Object data) {
        return new JsonResponseGenerator(status, data);
    }
}
