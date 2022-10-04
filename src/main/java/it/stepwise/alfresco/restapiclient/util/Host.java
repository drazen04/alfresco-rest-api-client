package it.stepwise.alfresco.restapiclient.util;

public class Host {

    private String hostURL;

    public Host(String protocol, String domain, int port) {
        this.hostURL = protocol + "://" + domain + ":" + port;
    }

    public Host(String protocol, String domain) {
        this.hostURL = protocol + "://" + domain;
    }

    public String getHostURL() {
        return hostURL;
    }
    
}