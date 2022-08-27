package it.stepwise.alfresco.restapiclient.util;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static it.stepwise.alfresco.restapiclient.common.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class APIUtilTest {

    @Test
    public void test_url_composition() {
        String nodeId = UUID.randomUUID().toString();
        String URL = APIUtil.composeURL(
                BASE_URL_CORE_NODES_API,
                (url) -> url.replace(NUM_VERSION_PLACEHOLDER, "1"),
                (url) -> url.replace(NODE_ID_PLACEHOLDER, nodeId)
        );

        assertEquals("alfresco/api/-default-/public/alfresco/versions/1/nodes/" + nodeId, URL);
    }
}