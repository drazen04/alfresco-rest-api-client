package it.stepwise.alfresco.restapiclient.util;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResponseEitherTest {

    @Test
    void handle_error_value() {

        // Given
        ResponseEither<ErrorResponse, JSONObject> responseEitherWithError = ResponseEither.error(new ErrorResponse(400, "api not exist", "check for typos"));
        ResponseEither<ErrorResponse, JSONObject> responseEitherNullError = ResponseEither.error(null);

        // Then
        assertTrue(responseEitherWithError.hasError());
        assertFalse(responseEitherNullError.hasError());

    }
}