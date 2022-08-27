package it.stepwise.alfresco.restapiclient.util;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseEitherTest {

    @Test
    void handle_error_value() {
        // Given
        ResponseEither<Error, JSONObject> responseEitherWithError = ResponseEither.error(new Error(HttpStatus.SC_BAD_REQUEST, "api not exist", "check for typos"));
        ResponseEither<Error, JSONObject> responseEitherNullError = ResponseEither.error(null);

        // Then
        assertTrue(responseEitherWithError.hasError());
        assertFalse(responseEitherNullError.hasError());

    }
}