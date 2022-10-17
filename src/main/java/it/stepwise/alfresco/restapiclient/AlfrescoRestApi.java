package it.stepwise.alfresco.restapiclient;

import it.stepwise.alfresco.restapiclient.util.Host;

// TODO: cambiare nome non appena è finito il trasferimento delle funzioni da importud
public class AlfrescoRestApi {

    private String ticket;
    private final Host host;
    private final int numVersion;

    private final String user;
    private final String password;
    
    public AlfrescoRestApi(Host host, String user, String password) {
        this.host = host;
        this.numVersion = 1;
        this.user = user;
        this.password = password;
    }

    public AlfrescoRestApi(Host host, int numVersion, String user, String password) {
        this.host = host;
        this.numVersion = numVersion;
        this.user = user;
        this.password = password;
    }

    public Host getHost() {
        return host;
    }

    public int getNumVersion() {
        return numVersion;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}

/*
 * AlfrescoRestApi alfrescoRest = new AlfrescoRestApi(tipi di auth)
 * NodesApi nodesApi = new NodesApi(alfrescoRest, version);
 *
 * return service.getNode()
 *
 * SearchApi nodesApi = new SearchApi(alfrescoRest, version);
 *
 * nodesApi.createNode()
 *
 */
