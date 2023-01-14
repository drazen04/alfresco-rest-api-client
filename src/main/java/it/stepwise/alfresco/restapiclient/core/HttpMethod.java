package it.stepwise.alfresco.restapiclient.core;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.InputBody;
import it.stepwise.alfresco.restapiclient.common.MimetypeConstants;
import it.stepwise.alfresco.restapiclient.util.APIUtil;
import it.stepwise.alfresco.restapiclient.util.ErrorResponse;
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
    public ResponseEither<ErrorResponse, JSONObject> get(String url, int httpSuccessCode) {
        
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Authorization",
                            APIUtil.getBasicAuthenticationHeader(this.alfrescoRestApi.getUser(),
                                    alfrescoRestApi.getPassword()))
                    .header("Accept", MimetypeConstants.APPLICATION_JSON)
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            JSONObject responseJson = new JSONObject(httpResponse.body());
            if (httpResponse.statusCode() != httpSuccessCode) {
                JSONObject error = responseJson.getJSONObject("error");
                return ResponseEither.error(
                        new ErrorResponse(httpResponse.statusCode(), error.getString("errorKey"), error.getString("briefSummary")));
            }
            
            return ResponseEither.data(responseJson.getJSONObject("entry"));
            
        } catch (Exception e) {
            
            return ResponseEither.error(new ErrorResponse(500, "Internal server error", e.getMessage()));
            
        }
        
    }

    @Override
    public ResponseEither<ErrorResponse, JSONObject> post(String url, InputBody inputBody, int httpSuccessCode) {

        HttpClient httpClient = HttpClient.newHttpClient();

        try {

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Authorization",
                            APIUtil.getBasicAuthenticationHeader(
                                    this.alfrescoRestApi.getUser(),
                                    this.alfrescoRestApi.getPassword()))
                    .header("Accept", MimetypeConstants.APPLICATION_JSON)
                    .POST(HttpRequest.BodyPublishers.ofString(inputBody.toJSON().toString()))
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

    @Override
    public ResponseEither<ErrorResponse, JSONObject> delete(String url, int httpSuccessCode) {

        HttpClient httpClient = HttpClient.newHttpClient();

        try {

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Authorization",
                            APIUtil.getBasicAuthenticationHeader(
                                    this.alfrescoRestApi.getUser(),
                                    this.alfrescoRestApi.getPassword()))
                    .DELETE()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() != httpSuccessCode) {
                JSONObject responseJson = new JSONObject(httpResponse.body());
                JSONObject error = responseJson.getJSONObject("error");
                return ResponseEither.error(
                        new ErrorResponse(httpResponse.statusCode(), error.getString("errorKey"), error.getString("briefSummary")));
            }

            JSONObject responseObj = new JSONObject();
            return ResponseEither.data(responseObj);

        } catch (Exception e) {

            return ResponseEither.error(new ErrorResponse(500, "Internal server error", e.getMessage()));

        }

    }

    @Override
    public ResponseEither<ErrorResponse, JSONObject> postWithoutBody(String url, int httpSuccessCode) {

        HttpClient httpClient = HttpClient.newHttpClient();

        try {

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Authorization",
                            APIUtil.getBasicAuthenticationHeader(
                                    this.alfrescoRestApi.getUser(),
                                    this.alfrescoRestApi.getPassword()))
                    .header("Accept", MimetypeConstants.APPLICATION_JSON)
                    .POST(HttpRequest.BodyPublishers.noBody())
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