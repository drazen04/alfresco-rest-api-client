package it.stepwise.alfresco.restapiclient;

import org.json.JSONObject;

import it.stepwise.alfresco.restapiclient.core.HttpMethod;
import it.stepwise.alfresco.restapiclient.core.nodes.NodeBodyCreate;
import it.stepwise.alfresco.restapiclient.core.nodes.NodesApi;
import it.stepwise.alfresco.restapiclient.queryparams.Include;
import it.stepwise.alfresco.restapiclient.util.Host;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;

public class TestApi {

    public static void main(String[] args) {

        Host host = new Host("", "");
        AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi(host, "", "");
        HttpMethod httpMethod = new HttpMethod(alfrescoRestApi);
        NodesApi nodesApi = new NodesApi(alfrescoRestApi, httpMethod);
        
        NodeBodyCreate nodeBodyCreate = new NodeBodyCreate("", "p");
        ResponseEither<it.stepwise.alfresco.restapiclient.util.Error, JSONObject> rs = nodesApi.createNode(
                "",
                nodeBodyCreate,
                Include.IS_LOCKED);

    }

}