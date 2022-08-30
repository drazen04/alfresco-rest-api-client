package it.stepwise.alfresco.restapiclient.util;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResponseEitherTest {

    @Test
    void handle_error_value() {

        // Given
        ResponseEither<Error, JSONObject> responseEitherWithError = ResponseEither.error(new Error(400, "api not exist", "check for typos"));
        ResponseEither<Error, JSONObject> responseEitherNullError = ResponseEither.error(null);

        // Then
        assertTrue(responseEitherWithError.hasError());
        assertFalse(responseEitherNullError.hasError());

    }
}