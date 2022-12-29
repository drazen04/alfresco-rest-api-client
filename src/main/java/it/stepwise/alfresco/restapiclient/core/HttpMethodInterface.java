package it.stepwise.alfresco.restapiclient.core;

import org.json.JSONObject;

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
 * @version 1.0.1
 * 
 * @lastUpdate 2022-11-29 - JS.
 * 
 */
public interface HttpMethodInterface {

    public ResponseEither<Error, JSONObject> get(String url, int httpSuccessCode);
    public ResponseEither<Error, JSONObject> post(String url, InputBody inputBody, int httpSuccessCode);
    public ResponseEither<Error, JSONObject> put(String url, InputBody inputBody, int httpSuccessCode);
    public ResponseEither<Error, JSONObject> delete(String url, int httpSuccessCode);
    
    public ResponseEither<Error, JSONObject> postWithoutBody(String url, int httpSuccessCode);
	
}