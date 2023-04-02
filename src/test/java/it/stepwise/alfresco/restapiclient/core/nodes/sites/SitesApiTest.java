package it.stepwise.alfresco.restapiclient.core.nodes.sites;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.core.sites.SitesApi;
import it.stepwise.alfresco.restapiclient.util.Host;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SitesApiTest {

    @Test
    public void siteExists() {
        Host host = new Host("http", "localhost");
        AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi(host, "admin", "admin");
        SitesApi sitesApi = new SitesApi(alfrescoRestApi);

        var response = sitesApi.getSite("test-suite-sites");

        assertFalse(response.hasError());
        assertTrue(response.hasData());
    }

}
