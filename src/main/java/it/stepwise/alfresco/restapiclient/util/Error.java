package it.stepwise.alfresco.restapiclient.util;

import org.apache.http.HttpStatus;

public class Error {

    private final HttpStatus httpStatus;
    private final String error;
    private final String message;

    public Error(HttpStatus httpStatus, String error, String message) {
        this.httpStatus = httpStatus;
        this.error = error;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
