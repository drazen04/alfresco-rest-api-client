package it.stepwise.alfresco.restapiclient.search.searchparams;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

import it.stepwise.alfresco.restapiclient.InputBody;

@JsonRootName(value = "query")
public class Query extends InputBody {
    
    @JsonInclude(Include.NON_NULL)
    private Language language;
    @JsonInclude(Include.NON_NULL)
    private String userQuery;
    private String query;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getUserQuery() {
        return userQuery;
    }

    public void setUserQuery(String userQuery) {
        this.userQuery = userQuery;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Query(Language language, String userQuery, String query) {
        this.language = language;
        this.userQuery = userQuery;
        this.query = query;
    }

    public Query(Language language, String query) {
        this.language = language;
        this.query = query;
    }

    public Query(String query) {
        this.query = query;
    }

}
