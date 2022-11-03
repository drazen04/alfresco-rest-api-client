package it.stepwise.alfresco.restapiclient.util;

/**
 *
 *
 * @param Errore
 * @param R
 */
public class ResponseEither<Errore, R> {

    private final Error error;
    private final R data;

    private ResponseEither(Error error, R data) {
        this.error = error;
        this.data = data;
    }

    public static <Errore, R> ResponseEither<Errore, R> data(R value) {
        return new ResponseEither<>(null, value);
    }

    public static <Errore, R> ResponseEither<Errore, R> error(Error value) {
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