package it.stepwise.alfresco.restapiclient.search;

import it.stepwise.alfresco.restapiclient.core.nodes.BodyResponse;
import it.stepwise.alfresco.restapiclient.util.ErrorResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.search.querybuilder.CMIS;
import it.stepwise.alfresco.restapiclient.search.querybuilder.Fts;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity.CMISCondition;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity.DBComp;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.enums.Operator;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.enums.Type;
import it.stepwise.alfresco.restapiclient.search.searchparams.Fields;
import it.stepwise.alfresco.restapiclient.search.searchparams.Include;
import it.stepwise.alfresco.restapiclient.search.searchparams.Language;
import it.stepwise.alfresco.restapiclient.search.searchparams.Paging;
import it.stepwise.alfresco.restapiclient.search.searchparams.Query;
import it.stepwise.alfresco.restapiclient.search.searchparams.Sort;
import it.stepwise.alfresco.restapiclient.util.Host;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;

import static org.junit.jupiter.api.Assertions.*;

public class SearchApiTest {

    private Host host = new Host("http", "localhost");
    private AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi(this.host, "admin", "admin");
    private SearchApi searchApi = new SearchApi(this.alfrescoRestApi);

    @Test
    public void t1_searchAFTSBasic() {

        SearchBody searchBody = new SearchBody();
        Query query = new Query("TYPE:\"cm:content\"");
        searchBody.setQuery(query);

        ResponseEither<ErrorResponse, BodyResponse> responseEither = this.searchApi.search(
            searchBody);

        System.out.println(responseEither.getData());

        assertNull(responseEither.getError());
        assertNotNull(responseEither.getData().getList());
        assertNotNull(responseEither.getData().getEntries());
    }

    @Test
    public void t2_checkSearchModel() {

        assertEquals(Language.AFTS.getValue(), "afts");
        assertEquals(Language.LUCENE.getValue(), "lucene");
        assertEquals(Language.CMIS.getValue(), "cmis");

        assertEquals(Include.ALLOWABLE_OPERATIONS.getValue(), "allowableOperations");
        assertEquals(Include.ASPECT_NAMES.getValue(), "aspectNames");
        assertEquals(Include.IS_LINK.getValue(), "isLink");
        assertEquals(Include.PATH.getValue(), "path");
        assertEquals(Include.PROPERTIES.getValue(), "properties");
        assertEquals(Include.ASSOCIATION.getValue(), "association");

        assertEquals(Fields.ID.getValue(), "id");
        assertEquals(Fields.NAME.getValue(), "name");
        assertEquals(Fields.SEARCH.getValue(), "search");

        assertEquals(Sort.Type.FIELD.getValue(), "FIELD");
        assertEquals(Sort.Type.DOCUMENT.getValue(), "DOCUMENT");
        assertEquals(Sort.Type.SCORE.getValue(), "SCORE");

    }

    @Test
    public void t3_checkSearchBodyStructure() {

        SearchBody searchBody = new SearchBody();
        Query query = new Query(Language.AFTS, "TYPE:\"cm:content\"");
        searchBody.setQuery(query);
        Include[] include = new Include[] {Include.IS_LINK};
        searchBody.setInclude(include);
        Sort[] sort = new Sort[] {new Sort("cm:name")};
        searchBody.setSort(sort);
        Paging paging = new Paging(10, 5);
        searchBody.setPaging(paging);
        Fields[] fields = new Fields[] {Fields.NAME};
        searchBody.setFields(fields);

        assertEquals(searchBody.getQuery().getQuery(), "TYPE:\"cm:content\"");
        assertEquals(searchBody.getQuery().getLanguage().getValue(), "afts");
        assertEquals(searchBody.getInclude().length, 1);
        assertEquals(searchBody.getSort().length, 1);
        assertEquals(searchBody.getSort()[0].getField(), "cm:name");
        assertEquals(searchBody.getSort()[0].getType().getValue(), "FIELD");
        assertTrue(searchBody.getSort()[0].getAscending());
        assertEquals(searchBody.getPaging().getMaxItems(), 10);
        assertEquals(searchBody.getPaging().getSkipCount(), 5);
        assertEquals(searchBody.getFields().length, 1);
        assertEquals(searchBody.getFields()[0].getValue(), "name");

    }

    @Test
    public void t4_checkFTSQueryBuilder() throws JsonProcessingException {

        SearchBody searchBody = new SearchBody();
        Fts fts = Fts.ftsBuilder()
            .EXACTTYPE("cm:content")
            .AND()
            .NOT_PROP("cm:title", "test.docx")
            .AND()
            .ASPECT("cm:titled");

        Query query = new Query(fts.buildQuery());
        searchBody.setQuery(query);

        assertEquals(searchBody.getQuery().getQuery(), ("EXACTTYPE:\"cm:content\" AND !cm:title:\"test.docx\" AND ASPECT:\"cm:titled\""));

    }

    @Test
    public void t5_searchAFTSComplex() throws JsonProcessingException {

        SearchBody searchBody = new SearchBody();
        Query query = new Query("TYPE:\"cm:content\"");
        searchBody.setQuery(query);
        Include[] include = new Include[] {Include.IS_LINK};
        searchBody.setInclude(include);
        Sort[] sort = new Sort[] {new Sort("cm:title")};
        searchBody.setSort(sort);
        Paging paging = new Paging(10, 5);
        searchBody.setPaging(paging);

        ResponseEither<ErrorResponse, BodyResponse> responseEither = this.searchApi.search(
            searchBody);

        assertNull(responseEither.getError());

    }

    @Test
    public void t6_searchLucene() throws JsonProcessingException {

        SearchBody searchBody = new SearchBody();
        Query query = new Query(Language.LUCENE, "cm\\:name:\"Test.docx\"");
        searchBody.setQuery(query);
        Include[] include = new Include[] {Include.IS_LINK, Include.ALLOWABLE_OPERATIONS, Include.PATH};
        searchBody.setInclude(include);
        Sort[] sort = new Sort[] {new Sort("cm:name")};
        searchBody.setSort(sort);
        Paging paging = new Paging(10, 5);
        searchBody.setPaging(paging);

        ResponseEither<ErrorResponse, BodyResponse> responseEither = this.searchApi.search(
            searchBody);

        assertNull(responseEither.getError());

    }

    @Test
    public void t7_searchCmis() throws JsonProcessingException {

        SearchBody searchBody = new SearchBody();
        Query query = new Query(Language.CMIS, "select * from cmis:document where cmis:name='Test.docx'");
        searchBody.setQuery(query);

        ResponseEither<ErrorResponse, BodyResponse> responseEither = this.searchApi.search(
            searchBody);

        assertNull(responseEither.getError());

    }
    
    @Test
    public void t8_checkCMISQueryBuilder() {

        SearchBody searchBody = new SearchBody();
        CMIS cmis = CMIS.withType(Type.CMIS_ALFRESCO)
            .SELECT(new DBComp("*"))
            .FROM(new DBComp("cmis:document"))
            .WHERE(new CMISCondition("cmis:name", "Test.docx", Operator.EQUALS));

        Query query = new Query(Language.CMIS, cmis.buildQuery());
        searchBody.setQuery(query);

        ResponseEither<ErrorResponse, BodyResponse> responseEither = this.searchApi.search(searchBody);

        assert(responseEither.getData().getEntries().isEmpty());

    }

}
