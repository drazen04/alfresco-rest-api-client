package it.stepwise.alfresco.restapiclient.core;

import it.stepwise.alfresco.restapiclient.core.nodes.BodyResponse;
import org.json.JSONObject;

import it.stepwise.alfresco.restapiclient.InputBody;
import it.stepwise.alfresco.restapiclient.util.ErrorResponse;
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

    public ResponseEither<ErrorResponse, JSONObject> get(String url, int httpSuccessCode);
    public ResponseEither<ErrorResponse, BodyResponse> post(String url, InputBody inputBody, int httpSuccessCode);
    public ResponseEither<ErrorResponse, BodyResponse> delete(String url, int httpSuccessCode);
    
    public ResponseEither<ErrorResponse, JSONObject> postWithoutBody(String url, int httpSuccessCode);
	
}