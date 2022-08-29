package it.stepwise.alfresco.restapiclient.core.nodes;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.util.APIUtil;
import it.stepwise.alfresco.restapiclient.util.Error;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public ResponseEither<Error, JSONObject> getNode(String nodeId) {
        String url = buildNodeUrl(nodeId);

        try (
                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(new HttpGet(url))
        ) {
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200) {
                Error error = new Error
                        (
                        response.getStatusLine().getStatusCode(),
                        response.getStatusLine().getReasonPhrase(),
                        ""
                        );
                return ResponseEither.error(error);
            }

            String result = EntityUtils.toString(response.getEntity());

            // TODO: create/handle specific response
            JSONObject jsonResponse = new JSONObject(result);
//            JSONArray entries = jsonResponse.getJSONObject("list").getJSONArray("entries");

            return ResponseEither.data(jsonResponse);

        } catch (Exception e) {
            return ResponseEither.error(new Error(500, "Internal server error",""));
        }
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
