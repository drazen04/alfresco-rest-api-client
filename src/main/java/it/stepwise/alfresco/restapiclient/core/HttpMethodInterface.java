package it.stepwise.alfresco.restapiclient.core;

import org.json.JSONObject;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.InputBody;
import it.stepwise.alfresco.restapiclient.util.Error;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;

/**
 * <h1>HttpMethodInterface.java</h1>
 * 
 * <p>
 * Interface for http methods
 * </p>
 * 
 * @since 1.0.0
 * @version 1.0.0
 * 
 * @lastUpdate 2022-10-18 - Daniele Del Vecchio.
 * 
 */
public interface HttpMethodInterface {

    public ResponseEither<Error, JSONObject> HttpPost(String url, InputBody inputBody, int httpSuccessCode);
    public ResponseEither<Error, JSONObject> HttpDelete(String url, int httpSuccessCode, AlfrescoRestApi alfrescoRestApi);
    
    public ResponseEither<Error, JSONObject> HttpPostWithoutBody(String url, int httpSuccessCode, AlfrescoRestApi alfrescoRestApi);
	
}