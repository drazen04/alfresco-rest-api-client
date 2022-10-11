package it.stepwise.alfresco.restapiclient.core.nodes;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.InputBody;
import it.stepwise.alfresco.restapiclient.queryparams.Include;
import it.stepwise.alfresco.restapiclient.util.APIUtil;
import it.stepwise.alfresco.restapiclient.util.Error;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static it.stepwise.alfresco.restapiclient.common.Constants.*;

public class NodesApi {

    private final AlfrescoRestApi alfrescoRestApi;
    private final int numVersion;

    public NodesApi(AlfrescoRestApi alfrescoRestApi) {
        this.alfrescoRestApi = alfrescoRestApi;
        this.numVersion = 1;
    }

    public NodesApi(AlfrescoRestApi alfrescoRestApi, int numVersion) {
        this.alfrescoRestApi = alfrescoRestApi;
        this.numVersion = numVersion;
    }

    public ResponseEither<Error, JSONObject> createNode(String nodeId, boolean autoRename, NodeBodyCreate nodeBodyCreate, /*TODO: insert fields*/Include... include) {
        String url = buildNodeUrl(nodeId);

        String urlCreateAutoRename =
                APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/children") + "?" + "autoRename=" + false;

        String urlCreateInclude =
                include.length != 0 ?
                        APIUtil.composeURL(urlCreateAutoRename, (urlComposed) -> urlComposed + "/children") + "?" + "include=" + Stream.of(include).map(incl -> incl.value).collect(Collectors.joining(",")) :
                        APIUtil.composeURL(urlCreateAutoRename, (urlComposed) -> urlComposed + "/children");

        return HttpPost(urlCreateInclude, nodeBodyCreate, 201);
    }

    public ResponseEither<Error, JSONObject> deleteNode(String nodeId, boolean permanent) {
        String url = buildNodeUrl(nodeId);

        String urlDelete =
                APIUtil.composeURL(url, (urlComposed) -> urlComposed + "?" + "permanent=" + false);

        return HttpDelete(urlDelete, 204);
    }

    public ResponseEither<Error, JSONObject> createNode(String nodeId, NodeBodyCreate nodeBodyCreate, /*TODO: insert fields*/Include... include) {

        return createNode(nodeId, false, nodeBodyCreate, include);
    }

    public ResponseEither<Error, JSONObject> updateNode(String nodeId, JSONObject jsonBodyUpdate, /*TODO: insert fields*/Include... include) {
        String url = buildNodeUrl(nodeId);
        return null;
    }

    public ResponseEither<Error, JSONObject> lockNode(String nodeId, NodeBodyLock nodeBodyLock, /*TODO: insert fields*/Include... include) {
        String url = buildNodeUrl(nodeId);

        String urlLock =
                include.length != 0 ?
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/lock") + "?" + "include=" + Stream.of(include).map(incl -> incl.value).collect(Collectors.joining(",")) :
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/lock");

        return HttpPost(urlLock, nodeBodyLock, 200);
    }

    public ResponseEither<Error, JSONObject> unlockNode(String nodeId, /*TODO: insert fields*/Include... include) {
        String url = buildNodeUrl(nodeId);

        String urlLock =
                include.length != 0 ?
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/unlock") + "?" + "include=" + Stream.of(include).map(incl -> incl.value).collect(Collectors.joining(",")) :
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/unlock");
        return HttpPostWithoutBody(urlLock, 200);
    }

    public ResponseEither<Error, JSONObject> moveNode(String nodeId, NodeBodyMove nodeBodyMove, Include... include) {
        String url = buildNodeUrl(nodeId);

        String urlMove =
                include.length != 0 ?
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/move") + "?" + "include=" + Stream.of(include).map(incl -> incl.value).collect(Collectors.joining(",")) :
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/move");

        return HttpPost(urlMove, nodeBodyMove, 200);
    }

    public ResponseEither<Error, JSONObject> copyNode(String nodeId, NodeBodyCopy nodeBodyMove, /*TODO: insert fields*/Include... include) {
        String url = buildNodeUrl(nodeId);

        String urlMove =
                include.length != 0 ?
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/copy") + "?" + "include=" + Stream.of(include).map(incl -> incl.value).collect(Collectors.joining(",")) :
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/copy");

        return HttpPost(urlMove, nodeBodyMove, 201);
    }

    /**
     * TODO: make interface for HTTP Method
     * @param url
     * @param inputBody
     * @return
     */
    public ResponseEither<Error, JSONObject> HttpPost(String url, InputBody inputBody, int httpSuccessCode) {

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Authorization", APIUtil.getBasicAuthenticationHeader(alfrescoRestApi.getUser(), alfrescoRestApi.getPassword()))
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(inputBody.toJSON().toString()))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != httpSuccessCode) {
                JSONObject responseJson = new JSONObject(response.body());
                JSONObject error = responseJson.getJSONObject("error");
                return ResponseEither.error(new Error(response.statusCode(), error.getString("errorKey"), error.getString("briefSummary")));
            }

            JSONObject responseObj = new JSONObject(response.body());
            return ResponseEither.data(responseObj);
        } catch (Exception e) {
            return ResponseEither.error(new Error(500, "Internal server error", e.getMessage()));
        }
    }

    public ResponseEither<Error, JSONObject> HttpDelete(String url, int httpSuccessCode) {

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Authorization", APIUtil.getBasicAuthenticationHeader(alfrescoRestApi.getUser(), alfrescoRestApi.getPassword()))
                    .DELETE()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != httpSuccessCode) {
                JSONObject responseJson = new JSONObject(response.body());
                JSONObject error = responseJson.getJSONObject("error");
                return ResponseEither.error(new Error(response.statusCode(), error.getString("errorKey"), error.getString("briefSummary")));
            }

            JSONObject responseObj = new JSONObject();
            return ResponseEither.data(responseObj);
        } catch (Exception e) {
            return ResponseEither.error(new Error(500, "Internal server error", e.getMessage()));
        }
    }

    public ResponseEither<Error, JSONObject> HttpPostWithoutBody(String url, int httpSuccessCode) {

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Authorization", APIUtil.getBasicAuthenticationHeader(alfrescoRestApi.getUser(), alfrescoRestApi.getPassword()))
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != httpSuccessCode) {
                JSONObject responseJson = new JSONObject(response.body());
                JSONObject error = responseJson.getJSONObject("error");
                return ResponseEither.error(new Error(response.statusCode(), error.getString("errorKey"), error.getString("briefSummary")));
            }

            JSONObject responseObj = new JSONObject(response.body());
            return ResponseEither.data(responseObj);
        } catch (Exception e) {
            return ResponseEither.error(new Error(500, "Internal server error", e.getMessage()));
        }
    }

    public ResponseEither<Error, JSONObject> getListNodeChildren(String nodeId) {
        String url = buildNodeUrl(nodeId);

        String urlChildren =  APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/children");

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(urlChildren))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Authorization", APIUtil.getBasicAuthenticationHeader(alfrescoRestApi.getUser(), alfrescoRestApi.getPassword()))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                JSONObject responseJson = new JSONObject(response.body());
                JSONObject error = responseJson.getJSONObject("error");
                return ResponseEither.error(new Error(response.statusCode(), error.getString("errorKey"), error.getString("briefSummary")));
            }

            JSONObject responseArray = new JSONObject(response.body());
            return ResponseEither.data(responseArray);
        } catch (Exception e) {
            return ResponseEither.error(new Error(500, "Internal server error", e.getMessage()));
        }
    }

    public ResponseEither<Error, JSONObject> getNode(String nodeId) {
        String url = buildNodeUrl(nodeId);

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .version(HttpClient.Version.HTTP_2)
                    .header("Authorization", APIUtil.getBasicAuthenticationHeader(alfrescoRestApi.getUser(), alfrescoRestApi.getPassword()))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject responseJson = new JSONObject(response.body());
            if (response.statusCode() != 200) {
                JSONObject error = responseJson.getJSONObject("error");
                return ResponseEither.error(new Error(response.statusCode(), error.getString("errorKey"), error.getString("briefSummary")));
            }
            return ResponseEither.data(responseJson.getJSONObject("entry"));
        } catch (Exception e) {
            return ResponseEither.error(new Error(500, "Internal server error", e.getMessage()));
        }

    }

    private String buildNodeUrl(String nodeId) {
        return APIUtil.composeURL(
                BASE_URL_CORE_NODES_API,
                (urlComposed) -> alfrescoRestApi.getHost().getHostURL() + "/" + urlComposed,
                (urlComposed) -> urlComposed.replace(NUM_VERSION_PLACEHOLDER, String.valueOf(this.numVersion)),
                (urlComposed) -> urlComposed.replace(NODE_ID_PLACEHOLDER, nodeId)
        );
    }

}
