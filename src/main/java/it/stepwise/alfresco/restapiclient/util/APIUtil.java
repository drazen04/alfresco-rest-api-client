package it.stepwise.alfresco.restapiclient.util;

import it.stepwise.alfresco.restapiclient.common.Constants;

public class APIUtil {

    public static String insertVersionInURL(String baseURL, int numVersion) {
        return replacePlaceholderInURL(baseURL, Constants.NUM_VERSION_PLACEHOLDER, Integer.toString(numVersion));
    }

    public static String insertNodeIdInURL(String baseURL, String nodeId) {
        return replacePlaceholderInURL(baseURL, Constants.NODE_ID_PLACEHOLDER, nodeId);
    }

    public static String replacePlaceholderInURL(String baseURL, String placeholder, String value) {
        return baseURL.replace(placeholder, value);
    }
}
