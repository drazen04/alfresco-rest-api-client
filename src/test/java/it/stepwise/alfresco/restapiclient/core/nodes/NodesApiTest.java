package it.stepwise.alfresco.restapiclient.core.nodes;

import it.stepwise.alfresco.restapiclient.search.SearchApi;
import it.stepwise.alfresco.restapiclient.search.SearchBody;
import it.stepwise.alfresco.restapiclient.search.searchparams.Query;
import it.stepwise.alfresco.restapiclient.util.ErrorResponse;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.core.nodes.nodesparams.Include;
import it.stepwise.alfresco.restapiclient.util.Host;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;

public class NodesApiTest {

    @BeforeAll
    public static void delete_test_nodes() {
        Host host = new Host("http", "localhost");
        AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi(host, "admin", "admin");
        NodesApi nodesApi = new NodesApi(alfrescoRestApi);

        // TODO: I should implement SitesApi
        SearchApi searchApi = new SearchApi(alfrescoRestApi);
        SearchBody searchBodyFolderTest = new SearchBody();
        Query queryFolderTest = new Query("TYPE:'cm:folder' AND SITE:'test-suite-sites' AND cm:name:'test-folder'");
        searchBodyFolderTest.setQuery(queryFolderTest);


        ResponseEither<ErrorResponse, BodyResponse> searchResponseFolderTest = searchApi.search(searchBodyFolderTest);

        JSONObject entryFolderTest = searchResponseFolderTest.getData().getFirstEntry();
        String id = entryFolderTest.getString("id");
        String parentid = entryFolderTest.getString("parentId");
        ResponseEither<ErrorResponse, BodyResponse> responseEither = nodesApi.deleteNode(id, true);

        if (!responseEither.hasError()) {
            nodesApi.createNode(parentid, new NodeBodyCreate("test-folder", "cm:folder"));
        }
    }

    @Test
    public void t1_getNode() {
        Host host = new Host("http", "localhost");
        AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi(host, "admin", "admin");
        NodesApi nodesApi = new NodesApi(alfrescoRestApi);

        ResponseEither<ErrorResponse, JSONObject> responseEither = nodesApi.getNode(
                "");

        System.out.println(responseEither.getData());

    }
    
    @Test
    public void t2_createNode() {
        Host host = new Host("http", "localhost");
        AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi(host, "admin", "admin");
        NodesApi nodesApi = new NodesApi(alfrescoRestApi);

        NodeBodyCreate nodeBodyCreate = new NodeBodyCreate("", "");
        ResponseEither<ErrorResponse, BodyResponse> responseEither = nodesApi.createNode(
                "",
                false,
                nodeBodyCreate,
                Include.IS_LOCKED);

        System.out.println(responseEither.getData());

    }

}