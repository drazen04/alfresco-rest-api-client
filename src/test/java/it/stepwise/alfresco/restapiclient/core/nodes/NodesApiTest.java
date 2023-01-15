package it.stepwise.alfresco.restapiclient.core.nodes;

import it.stepwise.alfresco.restapiclient.util.ErrorResponse;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.core.HttpMethod;
import it.stepwise.alfresco.restapiclient.core.nodes.nodesparams.Include;
import it.stepwise.alfresco.restapiclient.util.Host;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;

public class NodesApiTest {

    @BeforeAll
    public static void delete_test_nodes() {
        Host host = new Host("http", "localhost");
        AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi(host, "admin", "admin");

        NodesApi nodesApi = new NodesApi(alfrescoRestApi);

        // TODO: Remove hard-coded nodes, using SearchApi
        // TODO: I should implement SitesApi
        ResponseEither<ErrorResponse, JSONObject> responseEither = nodesApi.deleteNode(
                "dd23879f-57ab-47e0-914f-eb3df7b24dc9", true);
        if (!responseEither.hasError()) {
            ResponseEither<ErrorResponse, JSONObject> createNode = nodesApi.createNode(
                    "8f2105b4-daaf-4874-9e8a-2152569d109b", new NodeBodyCreate("test", "cm:folder"));
        }
    }

    @Test
    public void t1_getNode() {
        Host host = new Host("", "");
        AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi(host, "", "");
        HttpMethod httpMethod = new HttpMethod(alfrescoRestApi);
        NodesApi nodesApi = new NodesApi(alfrescoRestApi);

        ResponseEither<ErrorResponse, JSONObject> responseEither = nodesApi.getNode(
                "");

        System.out.println(responseEither.getData());

    }
    
    @Test
    public void t2_createNode() {
        Host host = new Host("", "");
        AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi(host, "", "");
        HttpMethod httpMethod = new HttpMethod(alfrescoRestApi);
        NodesApi nodesApi = new NodesApi(alfrescoRestApi);

        NodeBodyCreate nodeBodyCreate = new NodeBodyCreate("", "");
        ResponseEither<ErrorResponse, JSONObject> responseEither = nodesApi.createNode(
                "",
                false,
                nodeBodyCreate,
                Include.IS_LOCKED);

        System.out.println(responseEither.getData());

    }

}