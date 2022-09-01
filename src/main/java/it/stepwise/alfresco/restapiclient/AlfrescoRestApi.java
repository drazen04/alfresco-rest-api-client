package it.stepwise.alfresco.restapiclient;

import it.stepwise.alfresco.restapiclient.authentication.Tickets;
import it.stepwise.alfresco.restapiclient.common.Constants;
import it.stepwise.alfresco.restapiclient.util.Host;

import java.io.IOException;
import java.net.URISyntaxException;

// TODO: cambiare nome non appena è finito il trasferimento delle funzioni da importud
public class AlfrescoRestApi {

    // TODO: Aggiungere restanti API
    public static final String BASE_URL_SEARCH_API          = "alfresco/api/-default-/public/search/versions/" + Constants.NUM_VERSION_PLACEHOLDER + "/search";
    public static final String BASE_URL_AUTHENTICATION_API  = "alfresco/api/-default-/public/authentication/versions/" + Constants.NUM_VERSION_PLACEHOLDER + "/tickets";


    private String ticket;
    private final Host host;
    private int numVersion;

    // TODO: Costruttori per autenticazione
    public AlfrescoRestApi(Host host, String user, String password, int numVersion) {
        this.host = host;
        this.numVersion = numVersion;
        this.setTicket(user, password, numVersion);
    }
    public AlfrescoRestApi(String user, String password, int numVersion) {
        this.host = new Host("http", "localhost", 8080);
        this.numVersion = numVersion;
        this.setTicket(user, password, numVersion);
    }

    private void setTicket(String user, String password, int numVersion) {
        // TODO: Chiamata a BASE_URL_AUTHENTICATION_API
        Tickets tickets = new Tickets(this, "admin", "admin");
        try {
            this.ticket = tickets.login().getData();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            this.ticket = "";
        } catch (InterruptedException e) {
            e.printStackTrace();
            this.ticket = "";
        }
    }

    public String getTicket() {
        return ticket;
    }

    public Host getHost() {
        return host;
    }

    public int getNumVersion() {
        return numVersion;
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
 *
 *
 */
