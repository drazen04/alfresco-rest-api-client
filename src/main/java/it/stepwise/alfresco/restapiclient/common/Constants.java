package it.stepwise.alfresco.restapiclient.common;

public class Constants {

    // API URL
    public static final String BASE_URL_CORE_NODES_API = "alfresco/api/-default-/public/alfresco/versions/" + Constants.NUM_VERSION_PLACEHOLDER + "/nodes/" + Constants.NODE_ID_PLACEHOLDER;
    public static final String BASE_URL_SEARCH_API = "alfresco/api/-default-/public/search/versions/" + Constants.NUM_VERSION_PLACEHOLDER + "/search";

    // PLACEHOLDERS
    public static final String NUM_VERSION_PLACEHOLDER = "{num_version}";
    public static final String NODE_ID_PLACEHOLDER = "{nodeId}";
}
