package it.stepwise.alfresco.restapiclient.util;

import java.util.function.Function;
import java.util.stream.Stream;

public class APIUtil {

    @SafeVarargs
    public static String composeURL(String baseURL, Function<String, String>... fieldToCompose) {
        return Stream.of(fieldToCompose).reduce( Function.identity(), Function::andThen ).apply(baseURL);
    }
}
