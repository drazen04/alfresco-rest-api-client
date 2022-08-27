package it.stepwise.alfresco.restapiclient.util;

/**
 *
 *
 * @param <Error>
 * @param <R>
 */
public class ResponseEither<Error, R> {
    private final Error error;
    private final R data;


    private ResponseEither(Error error, R data) {
        this.error = error;
        this.data = data;
    }

    public static <L,R> ResponseEither<L,R> data(R value) {
        return new ResponseEither<>(null, value);
    }

    public static <Error,R> ResponseEither<Error,R> error(Error value) {
        return new ResponseEither<>(value, null);
    }

    public R getData() {
        return this.data;
    }

    public Error getError() {
        return this.error;
    }


    public boolean hasError() {
        return this.error != null;
    }

    public boolean hasData() {
        return this.data != null;
    }

}
