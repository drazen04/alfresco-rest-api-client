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
import it.stepwise.alfresco.restapiclient.util.Error;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;

public class NodesApi {

    private final AlfrescoRestApi alfrescoRestApi;
    private final int numVersion;

    private final HttpMethod httpMethod;
    
    public NodesApi(AlfrescoRestApi alfrescoRestApi, HttpMethod httpMethod) {
        this.alfrescoRestApi = alfrescoRestApi;
        this.numVersion = 1;
        this.httpMethod = httpMethod;
    }

    public NodesApi(AlfrescoRestApi alfrescoRestApi, int numVersion, HttpMethod httpMethod) {
        this.alfrescoRestApi = alfrescoRestApi;
        this.numVersion = numVersion;
        this.httpMethod = httpMethod;
    }

    public ResponseEither<Error, JSONObject> getNode(String nodeId) {
        
        String url = this.buildNodeUrl(nodeId);
        
        return this.httpMethod.get(url, 200);

    }
    
    public ResponseEither<Error, JSONObject> createNode(String nodeId, boolean autoRename, NodeBodyCreate nodeBodyCreate, /*TODO: insert fields*/Include... include) {
        String url = buildNodeUrl(nodeId);

        String urlCreateAutoRename =
                APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/children") + "?" + "autoRename=" + false;

        String urlCreateInclude =
                include.length != 0 ?
                        APIUtil.composeURL(urlCreateAutoRename, (urlComposed) -> urlComposed + "/children") + "?" + "include=" + Stream.of(include).map(incl -> incl.value).collect(Collectors.joining(",")) :
                        APIUtil.composeURL(urlCreateAutoRename, (urlComposed) -> urlComposed + "/children");
        
        return this.httpMethod.post(urlCreateInclude, nodeBodyCreate, 201);
    }

    public ResponseEither<Error, JSONObject> deleteNode(String nodeId, boolean permanent) {
        String url = buildNodeUrl(nodeId);

        String urlDelete =
                APIUtil.composeURL(url, (urlComposed) -> urlComposed + "?" + "permanent=" + false);

        return this.httpMethod.delete(urlDelete, 204);
    }

    public ResponseEither<Error, JSONObject> deleteNodeAssociation(String nodeId, String targetId, String assocType) {
        String url = buildNodeUrl(nodeId);

        String urlDeleteNodeAssociation =
            (assocType == null || assocType.equals("")) ?
                APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/targets/" + targetId) :
                APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/targets/" + targetId + "?" + "assocType=" + assocType);

        return this.httpMethod.delete(urlDeleteNodeAssociation, 204);
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

        return this.httpMethod.post(urlLock, nodeBodyLock, 200);
    }

    public ResponseEither<Error, JSONObject> unlockNode(String nodeId, /*TODO: insert fields*/Include... include) {
        String url = buildNodeUrl(nodeId);

        String urlLock =
                include.length != 0 ?
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/unlock") + "?" + "include=" + Stream.of(include).map(incl -> incl.value).collect(Collectors.joining(",")) :
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/unlock");
        
        return this.httpMethod.postWithoutBody(urlLock, 200);
    }

    public ResponseEither<Error, JSONObject> moveNode(String nodeId, NodeBodyMove nodeBodyMove, Include... include) {
        String url = buildNodeUrl(nodeId);

        String urlMove =
                include.length != 0 ?
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/move") + "?" + "include=" + Stream.of(include).map(incl -> incl.value).collect(Collectors.joining(",")) :
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/move");

        return this.httpMethod.post(urlMove, nodeBodyMove, 200);
    }

    public ResponseEither<Error, JSONObject> copyNode(String nodeId, NodeBodyCopy nodeBodyCopy, /*TODO: insert fields*/Include... include) {
        String url = buildNodeUrl(nodeId);

        String urlCopy =
                include.length != 0 ?
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/copy") + "?" + "include=" + Stream.of(include).map(incl -> incl.value).collect(Collectors.joining(",")) :
                        APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/copy");

        return this.httpMethod.post(urlCopy, nodeBodyCopy, 201);
    }
    
    /**
     * List node children
     * 
     * TODO: make interface for HTTP Method
     * @param nodeId
     * @return
     */
    public ResponseEither<Error, JSONObject> getListNodeChildren(String nodeId) {
        String url = buildNodeUrl(nodeId);

        String urlChildren =  APIUtil.composeURL(url, (urlComposed) -> urlComposed + "/children");

        return this.httpMethod.get(urlChildren, 200);
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