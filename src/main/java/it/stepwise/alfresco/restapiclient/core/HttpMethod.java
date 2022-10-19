package it.stepwise.alfresco.restapiclient.core;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.InputBody;
import it.stepwise.alfresco.restapiclient.util.APIUtil;
import it.stepwise.alfresco.restapiclient.util.Error;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;

/**
 * <h1>HttpMethod.java</h1>
 * 
 * <p>
 * Class to implement http methods for calls to rest api
 * </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * 
 * @lastUpdate 2022-10-18 - Daniele Del Vecchio.
 * 
 */
public class HttpMethod implements HttpMethodInterface {

    private final AlfrescoRestApi alfrescoRestApi;

    public HttpMethod(AlfrescoRestApi alfrescoRestApi) {
        this.alfrescoRestApi = alfrescoRestApi;
    }

    @Override
    public ResponseEither<Error, JSONObject> post(String url, InputBody inputBody, int httpSuccessCode) {

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Authorization",
                            APIUtil.getBasicAuthenticationHeader(
                                    this.alfrescoRestApi.getUser(),
                                    this.alfrescoRestApi.getPassword()))
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(inputBody.toJSON().toString()))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != httpSuccessCode) {
                JSONObject responseJson = new JSONObject(response.body());
                JSONObject error = responseJson.getJSONObject("error");
                return ResponseEither.error(
                        new Error(response.statusCode(), error.getString("errorKey"), error.getString("briefSummary")));
            }

            JSONObject responseObj = new JSONObject(response.body());
            return ResponseEither.data(responseObj);

        } catch (Exception e) {

            return ResponseEither.error(new Error(500, "Internal server error", e.getMessage()));

        }

    }

    @Override
    public ResponseEither<Error, JSONObject> delete(String url, int httpSuccessCode, AlfrescoRestApi alfrescoRestApi) {

        HttpClient httpClient = HttpClient.newHttpClient();

        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Authorization",
                            APIUtil.getBasicAuthenticationHeader(alfrescoRestApi.getUser(),
                                    alfrescoRestApi.getPassword()))
                    .DELETE()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != httpSuccessCode) {
                JSONObject responseJson = new JSONObject(response.body());
                JSONObject error = responseJson.getJSONObject("error");
                return ResponseEither.error(
                        new Error(response.statusCode(), error.getString("errorKey"), error.getString("briefSummary")));
            }

            JSONObject responseObj = new JSONObject();
            return ResponseEither.data(responseObj);

        } catch (Exception e) {

            return ResponseEither.error(new Error(500, "Internal server error", e.getMessage()));

        }

    }

    @Override
    public ResponseEither<Error, JSONObject> postWithoutBody(String url, int httpSuccessCode, AlfrescoRestApi alfrescoRestApi) {

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Authorization",
                            APIUtil.getBasicAuthenticationHeader(alfrescoRestApi.getUser(),
                                    alfrescoRestApi.getPassword()))
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != httpSuccessCode) {
                JSONObject responseJson = new JSONObject(response.body());
                JSONObject error = responseJson.getJSONObject("error");
                return ResponseEither.error(
                        new Error(response.statusCode(), error.getString("errorKey"), error.getString("briefSummary")));
            }

            JSONObject responseObj = new JSONObject(response.body());
            return ResponseEither.data(responseObj);
            
        } catch (Exception e) {
            
            return ResponseEither.error(new Error(500, "Internal server error", e.getMessage()));
            
        }
        
    }

}