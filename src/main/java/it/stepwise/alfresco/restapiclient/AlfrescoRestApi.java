package it.stepwise.alfresco.restapiclient;

import it.stepwise.alfresco.restapiclient.common.Constants;

// TODO: cambiare nome non appena è finito il trasferimento delle funzioni da importud
public class AlfrescoRestApi {

    // TODO: Aggiungere restanti API
    public static final String BASE_URL_SEARCH_API          = "alfresco/api/-default-/public/search/versions/" + Constants.NUM_VERSION_PLACEHOLDER + "/search";
    public static final String BASE_URL_AUTHENTICATION_API  = "alfresco/api/-default-/public/authentication/versions/" + Constants.NUM_VERSION_PLACEHOLDER + "/tickets";


    private String ticket;


    // TODO: Costruttori per autenticazione
    public AlfrescoRestApi(String user, String password) {
        this.setTicket(user, password);
    }

    private void setTicket(String user, String password) {
        // TODO: Chiamata a BASE_URL_AUTHENTICATION_API
        this.ticket = "TODO: eseguire Chiamata";
    }

    public String getTicket() {
        return ticket;
    }
}

/*
 * AlfrescoRestApi alfrescoRest = new AlfrescoRestApi(tipi di auth)
 * NodesApi nodesApi = new NodesApi(alfrescoRest, version);
 *
 * SearchApi nodesApi = new SearchApi(alfrescoRest, version);
 *
 * nodesApi.createNode()
 *
 *
 *
 */
