package com.ems.responsegenerator;

import org.springframework.http.HttpStatus;

public abstract class ResponseGenerator<T> {
    protected HttpStatus status;
    protected Object data;

    public ResponseGenerator(HttpStatus status, Object data) {
        this.status = status;
        this.data = data;
    }

    public abstract T sendResponse();
    public abstract void setDataLabel(String label);

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
