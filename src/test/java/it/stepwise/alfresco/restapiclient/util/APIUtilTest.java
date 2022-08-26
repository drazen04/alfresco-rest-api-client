package it.stepwise.alfresco.restapiclient.util;

import it.stepwise.alfresco.restapiclient.core.nodes.NodesApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class APIUtilTest {

    @Test
    public void when_replace_version_in_url_ok() {
        String uriWithVersionPlaceholder = NodesApi.BASE_URL_CORE_NODES_API;
        String uriWithVersionValue = APIUtil.insertVersionInURL(uriWithVersionPlaceholder, 1);

        String expectedUri = "alfresco/api/-default-/public/alfresco/versions/1/nodes/{nodeId}";

        assertEquals(expectedUri, uriWithVersionValue);
    }

    @Test
    public void when_replace_nodeId_in_url_ok() {
        String uriWithNodeIdPlaceholder = NodesApi.BASE_URL_CORE_NODES_API;
        String uriWithNodeIdValue = APIUtil.insertNodeIdInURL(uriWithNodeIdPlaceholder, "6efa8f55-59e9-44f6-a3be-a9f1e39a209a");

        System.out.println(uriWithNodeIdValue);
        String expectedUri = "alfresco/api/-default-/public/alfresco/versions/{num_version}/nodes/6efa8f55-59e9-44f6-a3be-a9f1e39a209a";

        assertEquals(expectedUri, uriWithNodeIdValue);
    }
}