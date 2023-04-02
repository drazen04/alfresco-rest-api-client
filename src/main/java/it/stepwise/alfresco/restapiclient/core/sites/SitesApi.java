package it.stepwise.alfresco.restapiclient.core.sites;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.core.HttpMethod;
import it.stepwise.alfresco.restapiclient.core.nodes.BodyResponse;
import it.stepwise.alfresco.restapiclient.core.nodes.nodesparams.Include;
import it.stepwise.alfresco.restapiclient.util.APIUtil;
import it.stepwise.alfresco.restapiclient.util.ErrorResponse;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;
import org.json.JSONObject;

import static it.stepwise.alfresco.restapiclient.common.Constants.*;

public class SitesApi {

    private final AlfrescoRestApi alfrescoRestApi;
    private final int numVersion;

    public SitesApi(AlfrescoRestApi alfrescoRestApi, int numVersion) {
        this.alfrescoRestApi = alfrescoRestApi;
        this.numVersion = numVersion;
    }

    public SitesApi(AlfrescoRestApi alfrescoRestApi) {
        this.alfrescoRestApi = alfrescoRestApi;
        this.numVersion = 1;
    }

    public ResponseEither<ErrorResponse, BodyResponse> createSite( SiteBodyCreate siteBodyCreate, boolean skipConfiguration, boolean skipAddToFavorites, /*TODO: insert fields*/Include... include) {
        String url = buildUrl();

        String urlWithDefaultParams =
                APIUtil.composeURL(url, (urlComposed) -> urlComposed) + "?" + "skipConfiguration=" + skipConfiguration + "&skipAddToFavorites=" + skipAddToFavorites;


        return new HttpMethod(this.alfrescoRestApi).post(urlWithDefaultParams, siteBodyCreate, 201);
    }

    public ResponseEither<ErrorResponse, JSONObject> getSite(String siteId) {
        return new HttpMethod(this.alfrescoRestApi).get(buildUrl(siteId), 201);
    }

    public ResponseEither<ErrorResponse, BodyResponse> createSite( SiteBodyCreate siteBodyCreate, /*TODO: insert fields*/Include... include) {
        return createSite(siteBodyCreate, false, false, include);
    }

    private String buildUrl() {
        return APIUtil.composeURL(
                BASE_URL_CORE_SITES_API,
                (urlComposed) -> alfrescoRestApi.getHost().getHostURL() + "/" + urlComposed,
                (urlComposed) -> urlComposed.replace(NUM_VERSION_PLACEHOLDER, String.valueOf(this.numVersion))
        );
    }
    private String buildUrl(String siteId) {
        return APIUtil.composeURL(
                BASE_URL_CORE_SITES_API,
                (urlComposed) -> alfrescoRestApi.getHost().getHostURL() + "/" + urlComposed,
                (urlComposed) -> urlComposed.replace(NUM_VERSION_PLACEHOLDER, String.valueOf(this.numVersion)),
                (urlComposed) -> urlComposed + "/" + siteId
        );
    }
}
