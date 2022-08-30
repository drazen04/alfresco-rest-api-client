package it.stepwise.alfresco.restapiclient.util;

public record Error(int httpStatus, String error, String message) {}
