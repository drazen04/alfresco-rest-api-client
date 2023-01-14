package it.stepwise.alfresco.restapiclient.core.nodes;

import it.stepwise.alfresco.restapiclient.util.ErrorResponse;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.core.HttpMethod;
import it.stepwise.alfresco.restapiclient.core.nodes.nodesparams.Include;
import it.stepwise.alfresco.restapiclient.util.Host;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;

public class NodesApiTest {

    private Host host = new Host("", "");
    private AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi(this.host, "", "");
    private HttpMethod httpMethod = new HttpMethod(this.alfrescoRestApi);
    private NodesApi nodesApi = new NodesApi(this.alfrescoRestApi);

    @Test
    public void t1_getNode() {

        ResponseEither<ErrorResponse, JSONObject> responseEither = this.nodesApi.getNode(
                "");

        System.out.println(responseEither.getData());

    }
    
    @Test
    public void t2_createNode() {

        NodeBodyCreate nodeBodyCreate = new NodeBodyCreate("", "");
        ResponseEither<ErrorResponse, JSONObject> responseEither = this.nodesApi.createNode(
                "",
                false,
                nodeBodyCreate,
                Include.IS_LOCKED);

        System.out.println(responseEither.getData());

    }

}