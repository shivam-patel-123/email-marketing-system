package com.ems.responsegenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.util.ClassUtils;

public class JsonResponseGenerator extends ResponseGenerator<JsonNode> {
    private String dataLabel;

    public JsonResponseGenerator(HttpStatus status, Object data) {
        super(status, data);
    }

    public JsonResponseGenerator(HttpStatus status, Object data, String dataLabel) {
        super(status, data);
        this.dataLabel = dataLabel;
    }

    public JsonNode sendResponse() {
        String response = "{" + "\"status\":" + "\"" + status + "\",";
        String dataField;
        boolean isDataPrimitive = ClassUtils.isPrimitiveOrWrapper(data.getClass());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dataField = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if (isDataPrimitive) {
            response += "\"data\":" +
                    "{\"" + dataLabel + "\":" + dataField + "}}";
        } else if (data instanceof String) {
            response += "\"data\": {\"" + dataLabel + "\":" + dataField + "}}";
        } else {
            response += "\"data\":" + dataField + "}";
        }

        try {
            return objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setDataLabel(String dataLabel) {
        this.dataLabel = dataLabel;
    }
}
