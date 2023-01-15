package it.stepwise.alfresco.restapiclient.core.nodes;

import static it.stepwise.alfresco.restapiclient.common.Constants.BASE_URL_CORE_NODES_API;
import static it.stepwise.alfresco.restapiclient.common.Constants.NODE_ID_PLACEHOLDER;
import static it.stepwise.alfresco.restapiclient.common.Constants.NUM_VERSION_PLACEHOLDER;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONObject;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.core.HttpMethod;
import it.stepwise.alfresco.restapiclient.core.nodes.nodesparams.Include;
import it.stepwise.alfresco.restapiclient.util.APIUtil;
import it.stepwise.alfresco.restapiclient.util.ErrorResponse;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;

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

    public ResponseEither<ErrorResponse, JSONObject> getNode(String nodeId) {
        
        String url = this.buildNodeUrl(nodeId);
        
        return new HttpMethod(this.alfrescoRestApi).get(url, 200);

    }
    
    public ResponseEither<ErrorResponse, BodyResponse> createNode(String nodeId, boolean autoRename, NodeBodyCreate nodeBodyCreate, /*TODO: insert fields*/Include... include) {
        String url = buildNodeUrl(nodeId);

        String urlCreateAutoRename =
                APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/children") + "?" + "autoRename=" + false;

        String urlCreateInclude =
                include.length != 0 ?
                        APIUtil.composeURL(urlCreateAutoRename, (urlComposed) -> urlComposed + "/children") + "?" + "include=" + Stream.of(include).map(incl -> incl.value).collect(Collectors.joining(",")) :
                        APIUtil.composeURL(urlCreateAutoRename, (urlComposed) -> urlComposed + "/children");
        
        return new HttpMethod(this.alfrescoRestApi).post(urlCreateInclude, nodeBodyCreate, 201);
    }

    public ResponseEither<ErrorResponse, BodyResponse> deleteNode(String nodeId, boolean permanent) {
        String url = buildNodeUrl(nodeId);

        String urlDelete =
                APIUtil.composeURL(url, (urlComposed) -> urlComposed + "?" + "permanent=" + false);

        return new HttpMethod(this.alfrescoRestApi).delete(urlDelete, 204);
    }

    public ResponseEither<ErrorResponse, BodyResponse> deleteNodeAssociation(String nodeId, String targetId, String assocType) {
        String url = buildNodeUrl(nodeId);

        String urlDeleteNodeAssociation =
            (assocType == null || assocType.equals("")) ?
                APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/targets/" + targetId) :
                APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/targets/" + targetId + "?" + "assocType=" + assocType);

        return new HttpMethod(this.alfrescoRestApi).delete(urlDeleteNodeAssociation, 204);
    }

    public ResponseEither<ErrorResponse, BodyResponse> createNode(String nodeId, NodeBodyCreate nodeBodyCreate, /*TODO: insert fields*/Include... include) {

        return createNode(nodeId, false, nodeBodyCreate, include);
    }

    public ResponseEither<ErrorResponse, JSONObject> updateNode(String nodeId, JSONObject jsonBodyUpdate, /*TODO: insert fields*/Include... include) {
        String url = buildNodeUrl(nodeId);
        return null;
    }

    public ResponseEither<ErrorResponse, BodyResponse> lockNode(String nodeId, NodeBodyLock nodeBodyLock, /*TODO: insert fields*/Include... include) {
        String url = buildNodeUrl(nodeId);

        String urlLock =
                include.length != 0 ?
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/lock") + "?" + "include=" + Stream.of(include).map(incl -> incl.value).collect(Collectors.joining(",")) :
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/lock");

        return new HttpMethod(this.alfrescoRestApi).post(urlLock, nodeBodyLock, 200);
    }

    public ResponseEither<ErrorResponse, JSONObject> unlockNode(String nodeId, /*TODO: insert fields*/Include... include) {
        String url = buildNodeUrl(nodeId);

        String urlLock =
                include.length != 0 ?
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/unlock") + "?" + "include=" + Stream.of(include).map(incl -> incl.value).collect(Collectors.joining(",")) :
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/unlock");
        
        return new HttpMethod(this.alfrescoRestApi).postWithoutBody(urlLock, 200);
    }

    public ResponseEither<ErrorResponse, BodyResponse> moveNode(String nodeId, NodeBodyMove nodeBodyMove, Include... include) {
        String url = buildNodeUrl(nodeId);

        String urlMove =
                include.length != 0 ?
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/move") + "?" + "include=" + Stream.of(include).map(incl -> incl.value).collect(Collectors.joining(",")) :
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/move");

        return new HttpMethod(this.alfrescoRestApi).post(urlMove, nodeBodyMove, 200);
    }

    public ResponseEither<ErrorResponse, BodyResponse> copyNode(String nodeId, NodeBodyCopy nodeBodyCopy, /*TODO: insert fields*/Include... include) {
        String url = buildNodeUrl(nodeId);

        String urlCopy =
                include.length != 0 ?
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/copy") + "?" + "include=" + Stream.of(include).map(incl -> incl.value).collect(Collectors.joining(",")) :
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/copy");

        return new HttpMethod(this.alfrescoRestApi).post(urlCopy, nodeBodyCopy, 201);
    }

    public ResponseEither<ErrorResponse, JSONObject> getListNodeChildren(String nodeId) {
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
                return ResponseEither.error(new ErrorResponse(response.statusCode(), error.getString("errorKey"), error.getString("briefSummary")));
            }

            JSONObject responseArray = new JSONObject(response.body());
            return ResponseEither.data(responseArray);
        } catch (Exception e) {
            return ResponseEither.error(new ErrorResponse(500, "Internal server error", e.getMessage()));
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