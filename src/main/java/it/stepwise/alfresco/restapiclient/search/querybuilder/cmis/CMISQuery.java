package it.stepwise.alfresco.restapiclient.search.querybuilder.cmis;

import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.bean.Pair;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <h1>CMISQuery.java</h1>
 *
 * <p>
 * </p>
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author Daniele Del Vecchio
 * @lastUpdate 2022-12-19 - Daniele Del Vecchio
 */
public class CMISQuery {

    private Map<String, String> selectStatement = new HashMap<String, String>();
    private Pair<String, String> fromStatement = new Pair<String, String>(StringUtils.EMPTY, StringUtils.EMPTY);
    private Pair<Pair<String, String>, String> joinStatement = new Pair<Pair<String,String>, String>(null, StringUtils.EMPTY);
    private Pair<String, String> joinObjectId = new Pair<String, String>(StringUtils.EMPTY, StringUtils.EMPTY);
    private String whereStatement = StringUtils.EMPTY;

    private String queryString = StringUtils.EMPTY;

    public CMISQuery() {
    }

    public Map<String, String> getSelectStatement() {
        return selectStatement;
    }

    public void setSelectStatement(Map<String, String> selectStatement) {
        this.selectStatement = selectStatement;
    }

    public Pair<String, String> getFromStatement() {
        return fromStatement;
    }

    public void setFromStatement(Pair<String, String> fromStatement) {
        this.fromStatement = fromStatement;
    }

    public Pair<Pair<String,String>, String> getJoinStatement() {
        return joinStatement;
    }

    public void setJoinStatement(Pair<Pair<String,String>, String> joinStatement) {
        this.joinStatement = joinStatement;
    }

    public Pair<String, String> getJoinObjectId() {
        return joinObjectId;
    }

    public void setJoinObjectId(Pair<String, String> joinObjectId) {
        this.joinObjectId = joinObjectId;
    }

    public String getWhereStatement() {
        return whereStatement;
    }

    public void setWhereStatement(String whereStatement) {
        this.whereStatement = whereStatement;
    }

    public String getStringValue() {
        return queryString;
    }

    public void setStringValue(String queryString) {
        this.queryString = queryString;
    }

}