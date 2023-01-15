package it.stepwise.alfresco.restapiclient.search;

import static it.stepwise.alfresco.restapiclient.common.Constants.BASE_URL_SEARCH_API;
import static it.stepwise.alfresco.restapiclient.common.Constants.NUM_VERSION_PLACEHOLDER;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.InputBody;
import it.stepwise.alfresco.restapiclient.common.MimetypeConstants;
import it.stepwise.alfresco.restapiclient.core.HttpMethod;
import it.stepwise.alfresco.restapiclient.util.APIUtil;
import it.stepwise.alfresco.restapiclient.util.ErrorResponse;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;

public class SearchApi {

    private final AlfrescoRestApi alfrescoRestApi;
    private final int numVersion;
    private final HttpMethod httpMethod;

    public SearchApi(AlfrescoRestApi alfrescoRestApi, int numVersion, HttpMethod httpMethod) {
        this.alfrescoRestApi = alfrescoRestApi;
        this.numVersion = numVersion;
        this.httpMethod = httpMethod;
    }

    public SearchApi(AlfrescoRestApi alfrescoRestApi, HttpMethod httpMethod) {
        this.alfrescoRestApi = alfrescoRestApi;
        this.numVersion = 1;
        this.httpMethod = httpMethod;
    }

    public SearchApi(AlfrescoRestApi alfrescoRestApi) {
        this.alfrescoRestApi = alfrescoRestApi;
        this.numVersion = 1;
        this.httpMethod = new HttpMethod(alfrescoRestApi);
    }

    public ResponseEither<ErrorResponse, JSONObject> search(InputBody inputBody) {
        
        String url = this.buildSearchUrl();
        
        //return this.httpMethod.post(url, queryBody, this.numVersion);
        return this.post(url, inputBody, 200);
    }
    
    private String buildSearchUrl() {
        return APIUtil.composeURL(
            BASE_URL_SEARCH_API,
                (urlComposed) -> alfrescoRestApi.getHost().getHostURL() + "/" + urlComposed,
                (urlComposed) -> urlComposed.replace(NUM_VERSION_PLACEHOLDER, String.valueOf(this.numVersion))
        );
    }

    // TODO move in utility class or create another function ad hoc in HttpMethod
    public ResponseEither<ErrorResponse, JSONObject> post(String url, InputBody inputBody, int httpSuccessCode) {

        HttpClient httpClient = HttpClient.newHttpClient();

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(Include.NON_NULL);
            JsonNode jsonNode = objectMapper.valueToTree(inputBody);
            String jsonStringBody = objectMapper.writeValueAsString(jsonNode);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Authorization",
                            APIUtil.getBasicAuthenticationHeader(
                                    this.alfrescoRestApi.getUser(),
                                    this.alfrescoRestApi.getPassword()))
                    .header("Accept", MimetypeConstants.APPLICATION_JSON)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonStringBody))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() != httpSuccessCode) {
                JSONObject responseJson = new JSONObject(httpResponse.body());
                JSONObject error = responseJson.getJSONObject("error");
                return ResponseEither.error(
                        new ErrorResponse(httpResponse.statusCode(), error.getString("errorKey"), error.getString("briefSummary")));
            }

            JSONObject responseObj = new JSONObject(httpResponse.body());
            return ResponseEither.data(responseObj);

        } catch (Exception e) {

            return ResponseEither.error(new ErrorResponse(500, "Internal server error", e.getMessage()));

        }

    }

}
