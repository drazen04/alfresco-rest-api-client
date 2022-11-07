package it.stepwise.alfresco.restapiclient.search;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.search.searchparams.Include;
import it.stepwise.alfresco.restapiclient.search.searchparams.Language;
import it.stepwise.alfresco.restapiclient.search.searchparams.Paging;
import it.stepwise.alfresco.restapiclient.search.searchparams.Query;
import it.stepwise.alfresco.restapiclient.search.searchparams.Sort;
import it.stepwise.alfresco.restapiclient.util.Host;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;

public class SearchApiTest {

    private Host host = new Host("", "");
    private AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi(this.host, "", "");
    private SearchApi searchApi = new SearchApi(this.alfrescoRestApi);

    private String defaultAftsQuery = "";

    @Test
    public void t1_searchAFTSBasic() throws JsonProcessingException {

        Query query = new Query(defaultAftsQuery);
        SearchBody searchBody = new SearchBody();
        searchBody.setQuery(query);

        ResponseEither<it.stepwise.alfresco.restapiclient.util.Error, JSONObject> responseEither = this.searchApi.search(
            searchBody);

        System.out.println(responseEither.getData());

    }

    @Test
    public void t2_searchAFTSLanguage() throws JsonProcessingException {

        Query query = new Query(Language.AFTS, defaultAftsQuery);
        SearchBody searchBody = new SearchBody();
        searchBody.setQuery(query);

        ResponseEither<it.stepwise.alfresco.restapiclient.util.Error, JSONObject> responseEither = this.searchApi.search(
            searchBody);

        System.out.println(responseEither.getData());

    }

    @Test
    public void t3_searchAFTSInclude() throws JsonProcessingException {

        Query query = new Query(defaultAftsQuery);
        SearchBody searchBody = new SearchBody();
        searchBody.setQuery(query);
        Include[] include = new Include[] {Include.IS_LINK};
        searchBody.setInclude(include);

        ResponseEither<it.stepwise.alfresco.restapiclient.util.Error, JSONObject> responseEither = this.searchApi.search(
            searchBody);

        System.out.println(responseEither.getData());

    }

    @Test
    public void t4_searchAFTSPaging() throws JsonProcessingException {

        Query query = new Query(defaultAftsQuery);
        SearchBody searchBody = new SearchBody();
        searchBody.setQuery(query);
        Paging paging = new Paging(10, 5);
        searchBody.setPaging(paging);

        ResponseEither<it.stepwise.alfresco.restapiclient.util.Error, JSONObject> responseEither = this.searchApi.search(
            searchBody);

        System.out.println(responseEither.getData());

    }

    @Test
    public void t5_searchAFTSSort() throws JsonProcessingException {

        Query query = new Query(defaultAftsQuery);
        SearchBody searchBody = new SearchBody();
        searchBody.setQuery(query);
        Sort[] sort = new Sort[] {new Sort("")};
        searchBody.setSort(sort);

        ResponseEither<it.stepwise.alfresco.restapiclient.util.Error, JSONObject> responseEither = this.searchApi.search(
            searchBody);

        System.out.println(responseEither.getData());

    }

    @Test
    public void t6_searchAFTSComplex() throws JsonProcessingException {

        SearchBody searchBody = new SearchBody();
        Query query = new Query(defaultAftsQuery);
        searchBody.setQuery(query);
        Include[] include = new Include[] {Include.IS_LINK};
        searchBody.setInclude(include);
        Sort[] sort = new Sort[] {new Sort("")};
        searchBody.setSort(sort);
        Paging paging = new Paging(10, 5);
        searchBody.setPaging(paging);

        ResponseEither<it.stepwise.alfresco.restapiclient.util.Error, JSONObject> responseEither = this.searchApi.search(
            searchBody);

        System.out.println(responseEither.getData());

    }

    @Test
    public void t6_searchLuceneComplex() throws JsonProcessingException {

        SearchBody searchBody = new SearchBody();
        Query query = new Query("");
        searchBody.setQuery(query);
        Include[] include = new Include[] {Include.IS_LINK, Include.ALLOWABLE_OPERATIONS, Include.PATH};
        searchBody.setInclude(include);
        Sort[] sort = new Sort[] {new Sort("")};
        searchBody.setSort(sort);
        Paging paging = new Paging(10, 5);
        searchBody.setPaging(paging);

        ResponseEither<it.stepwise.alfresco.restapiclient.util.Error, JSONObject> responseEither = this.searchApi.search(
            searchBody);

        System.out.println(responseEither.getData());

    }
    
}
