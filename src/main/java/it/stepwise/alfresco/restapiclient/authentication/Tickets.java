package it.stepwise.alfresco.restapiclient.authentication;

import com.fasterxml.jackson.databind.JsonNode;
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

// TODO: implement retry logic
public class Tickets {
    public static final String BASE_URL_AUTHENTICATION_API  = "alfresco/api/-default-/public/authentication/versions/" + Constants.NUM_VERSION_PLACEHOLDER + "/tickets";
    private final String userId;
    private final String password;
    private final AlfrescoRestApi alfrescoRestApi;

    public Tickets(AlfrescoRestApi alfrescoRestApi, String user, String password) {
        this.userId = user;
        this.password = password;
        this.alfrescoRestApi = alfrescoRestApi;
    }

    public ResponseEither<Error, String> login() throws URISyntaxException, IOException, InterruptedException {
        String url = APIUtil.composeURL(
                BASE_URL_AUTHENTICATION_API,
                (urlComposed) -> alfrescoRestApi.getHost().getHostURL() + "/" + urlComposed,
                (urlComposed) -> urlComposed.replace(NUM_VERSION_PLACEHOLDER, String.valueOf(this.alfrescoRestApi.getNumVersion()))
        );

        var credentials = new HashMap<String, String>() {{
            put("userId", userId);
            put ("password", password);
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(credentials);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .version(HttpClient.Version.HTTP_2)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode actualObj = objectMapper.readTree(response.body());
        JsonNode jsonNode = actualObj.get("entry");

        return ResponseEither.data( jsonNode.get("id").asText() );
    }
}
