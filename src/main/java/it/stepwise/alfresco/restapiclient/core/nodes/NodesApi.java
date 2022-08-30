package it.stepwise.alfresco.restapiclient.core.nodes;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.util.APIUtil;
import it.stepwise.alfresco.restapiclient.util.Error;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

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

    public ResponseEither<Error, JSONObject> createNode(String nodeId, JSONObject jsonCreateNode) {
        String url = buildNodeUrl(nodeId);
        return null;
    }

    public ResponseEither<Error, JSONObject> updateNode(String nodeId, JSONObject jsonBodyUpdate) {
        String url = buildNodeUrl(nodeId);
        return null;
    }

    public boolean lockNode(String nodeId, JSONObject nodeBodyLock) {
        String url = buildNodeUrl(nodeId);
        return false;
    }

    public ResponseEither<Error, JSONObject> moveNode(String nodeId, JSONObject jsonMoveNode) {
        String url = buildNodeUrl(nodeId);
        return null;
    }

    public ResponseEither<Error, JSONArray> getListNodeChildren(String nodeId) {
        String url = buildNodeUrl(nodeId);
        return null;
    }

    public ResponseEither<Error, JSONObject> getNode(String nodeId) throws URISyntaxException {
        String url = buildNodeUrl(nodeId);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        return null;
    }

    // TODO: Possibile enum per il body di lock node
//    public enum LifetimeEnum {
//        PERSISTENT("PERSISTENT"),
//
//        EPHEMERAL("EPHEMERAL");
//
//        private String value;
//
//        LifetimeEnum(String value) {
//            this.value = value;
//        }
//    }

    private String buildNodeUrl(String nodeId) {
        return APIUtil.composeURL(
                BASE_URL_CORE_NODES_API,
                (urlComposed) -> urlComposed.replace(NUM_VERSION_PLACEHOLDER, String.valueOf(this.numVersion)),
                (urlComposed) -> urlComposed.replace(NODE_ID_PLACEHOLDER, nodeId),
                (urlComposed) -> urlComposed + "?" + "alfTicket=" + this.alfrescoRestApi.getTicket()
        );
    }

}
