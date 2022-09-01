package it.stepwise.alfresco.restapiclient.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.common.Constants;
import it.stepwise.alfresco.restapiclient.util.APIUtil;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import static it.stepwise.alfresco.restapiclient.common.Constants.NUM_VERSION_PLACEHOLDER;

public class Tickets {
    public static final String BASE_URL_AUTHENTICATION_API  = "alfresco/api/-default-/public/authentication/versions/" + Constants.NUM_VERSION_PLACEHOLDER + "/tickets";
    private String user;
    private String password;
    private AlfrescoRestApi alfrescoRestApi;

    public Tickets(AlfrescoRestApi alfrescoRestApi, String user, String password) {
        this.user = user;
        this.password = password;
        this.alfrescoRestApi = alfrescoRestApi;
    }

    public ResponseEither<Error, String> login() throws URISyntaxException, IOException, InterruptedException {
        String url = APIUtil.composeURL(
                BASE_URL_AUTHENTICATION_API,
                (urlComposed) -> alfrescoRestApi.getHost().getHostURL() + "/" + urlComposed,
                (urlComposed) -> urlComposed.replace(NUM_VERSION_PLACEHOLDER, String.valueOf(this.alfrescoRestApi.getNumVersion()))
        );

        var values = new HashMap<String, String>() {{
            put("userId", "admin");
            put ("password", "admin");
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(values);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .version(HttpClient.Version.HTTP_2)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEither.data( (String) response.body() );
    }
}
