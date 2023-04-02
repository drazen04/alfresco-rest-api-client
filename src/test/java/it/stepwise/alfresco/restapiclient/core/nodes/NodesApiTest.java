package it.stepwise.alfresco.restapiclient.core.nodes;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.search.SearchApi;
import it.stepwise.alfresco.restapiclient.search.SearchBody;
import it.stepwise.alfresco.restapiclient.search.searchparams.Language;
import it.stepwise.alfresco.restapiclient.search.searchparams.Query;
import it.stepwise.alfresco.restapiclient.util.ErrorResponse;
import it.stepwise.alfresco.restapiclient.util.Host;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class NodesApiTest {


    @BeforeAll
    public static void delete_test_nodes() throws InterruptedException {
        Host host = new Host("http", "localhost");
        AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi(host, "admin", "admin");
        NodesApi nodesApi = new NodesApi(alfrescoRestApi);

        String siteId = getTestSiteId(alfrescoRestApi);

        // TODO: I should implement SitesApi
        SearchApi searchApi = new SearchApi(alfrescoRestApi);
        SearchBody searchBodyFolderTest = new SearchBody();
        Query queryFolderTest = new Query("TYPE:'cm:folder' AND SITE:'test-suite-sites' AND cm:name:'test-folder'");
        searchBodyFolderTest.setQuery(queryFolderTest);

        var searchResponseFolderTest = searchApi.search(searchBodyFolderTest);

        var data = searchResponseFolderTest.getData();
        if (!data.hasEntries()) {
            nodesApi.createNode(siteId, new NodeBodyCreate("test-folder", "cm:folder"));
            // TODO: ADJUST SOLR CRON.
            //       IT GET TOO MUCH TIME TO INDEXING FOR TESTING PURPOSE.
            //       SO SOLR SEARCH DOESN'T FIND NOTHING WITHOUT Thread.sleep
            Thread.sleep(20000);
             return;
        }
        JSONObject entryFolderTest = searchResponseFolderTest.getData().getFirstEntry();
        String id = entryFolderTest.getString("id");
        var responseEither = nodesApi.deleteNode(id, true);

        if (!responseEither.hasError()) {
            nodesApi.createNode(siteId, new NodeBodyCreate("test-folder", "cm:folder"));
            // TODO: ADJUST SOLR CRON.
            //       IT GET TOO MUCH TIME TO INDEXING FOR TESTING PURPOSE.
            //       SO SOLR SEARCH DOESN'T FIND NOTHING WITHOUT Thread.sleep
            Thread.sleep(20000);
        }
    }

    @Test
    public void nodeNoExist() {
        Host host = new Host("http", "localhost");
        AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi(host, "admin", "admin");
        NodesApi nodesApi = new NodesApi(alfrescoRestApi);

        var response = nodesApi.getNode(UUID.randomUUID().toString());

       assertTrue(response.hasError());
    }
    
    @Test
    public void nodeExists() {
        String nodeName = "node-test-" + UUID.randomUUID();
        AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi("admin", "admin");
        NodesApi nodesApi = new NodesApi(alfrescoRestApi);
        String parentId = getTestFolderId(alfrescoRestApi);
        var nodeBodyCreate = new NodeBodyCreate(nodeName, "cm:content");

        var response = nodesApi.createNode(parentId, false, nodeBodyCreate);

        assertFalse(response.hasError());
        assertEquals(response.getData().getEntry().getString("name"), nodeName);
    }

    private static String getTestFolderId(AlfrescoRestApi alfrescoRestApi) {
        SearchApi searchApi = new SearchApi(alfrescoRestApi);
        SearchBody searchBodyFolderTest = new SearchBody();
        Query queryFolderTest = new Query(Language.AFTS, "TYPE:'cm:folder' AND SITE:'test-suite-sites' AND cm:name:'test-folder'");
        searchBodyFolderTest.setQuery(queryFolderTest);

        var searchResponseFolderTest = searchApi.search(searchBodyFolderTest);

        return searchResponseFolderTest.getData().getFirstEntry().getString("id");
    }

    private static String getTestSiteId(AlfrescoRestApi alfrescoRestApi) {
        SearchApi searchApi = new SearchApi(alfrescoRestApi);
        SearchBody searchBodySiteTest = new SearchBody();
        Query querySiteTest = new Query("TYPE:'st:site' AND cm:name:'test-suite-sites'");
        searchBodySiteTest.setQuery(querySiteTest);

        var searchResponseFolderTest = searchApi.search(searchBodySiteTest);

        JSONObject entryFolderTest = searchResponseFolderTest
                .getData()
                .getList()
                .getJSONArray("entries")
                .getJSONObject(0)
                .getJSONObject("entry");
        return entryFolderTest.getString("id");
    }

    public ResponseEither<ErrorResponse, BodyResponse> searchNodeCreatedByName(String nodeName) {
        AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi("admin", "admin");
        SearchApi searchApi = new SearchApi(alfrescoRestApi);
        SearchBody searchBodyNodeCreated = new SearchBody();
        Query queryNodeCreated = new Query("TYPE:'cm:content' AND SITE:'test-suite-sites' AND cm:name:'" + nodeName + "'");
        searchBodyNodeCreated.setQuery(queryNodeCreated);
        return searchApi.search(searchBodyNodeCreated);
    }
    public ResponseEither<ErrorResponse, BodyResponse> createNodeByName(String nodeName) {
        AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi("admin", "admin");
        NodesApi nodesApi = new NodesApi(alfrescoRestApi);
        String parentId = getTestFolderId(alfrescoRestApi);
        var nodeBodyCreate = new NodeBodyCreate(nodeName, "cm:content");
        return nodesApi.createNode(parentId, false, nodeBodyCreate);
    }

}