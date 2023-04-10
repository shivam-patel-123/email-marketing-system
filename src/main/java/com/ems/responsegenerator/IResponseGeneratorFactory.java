package com.ems.responsegenerator;

import org.springframework.http.HttpStatus;

public interface IResponseGeneratorFactory {
    ResponseGenerator createResponseGenerator(HttpStatus status, Object data);
}
