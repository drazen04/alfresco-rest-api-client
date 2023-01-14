package it.stepwise.alfresco.restapiclient.util;
public class ResponseEither<L, R> {

    private final ErrorResponse errorResponse;
    private final R data;

    private ResponseEither(ErrorResponse errorResponse, R data) {
        this.errorResponse = errorResponse;
        this.data = data;
    }

    public static <L, R> ResponseEither<L, R> data(R value) {
        return new ResponseEither<>(null, value);
    }

    public static <L, R> ResponseEither<L, R> error(ErrorResponse value) {
        return new ResponseEither<>(value, null);
    }

    public R getData() {
        return this.data;
    }

    public ErrorResponse getError() {
        return this.errorResponse;
    }

    public boolean hasError() {
        return this.errorResponse != null;
    }

    public boolean hasData() {
        return this.data != null;
    }

}