package it.stepwise.alfresco.restapiclient.util;

public class Error {

    private final int httpStatus;
    private final String error;
    private final String message;

    public Error(int httpStatus, String error, String message) {
        this.httpStatus = httpStatus;
        this.error = error;
        this.message = message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
