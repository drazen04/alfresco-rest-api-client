package it.stepwise.alfresco.restapiclient.util;

public record ErrorResponse(int httpStatus, String error, String message) {}
