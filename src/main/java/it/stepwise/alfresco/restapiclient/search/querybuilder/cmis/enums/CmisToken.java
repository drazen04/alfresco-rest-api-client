package it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.enums;

/**
 * 
 * <h1>CmisToken.java</h1>
 *
 * <p>
 * </p>
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author Daniele Del Vecchio
 * @lastUpdate 2022-12-15 - Daniele Del Vecchio
 */
public enum CmisToken {
    
    SELECT("SELECT"),
    FROM("FROM"),
    WHERE("WHERE"),
    JOIN("JOIN"),
    ON("ON"),
    CONTAINS("CONTAINS"),
    GROUP_BY("GROUP BY");
    
    public final String cmisToken;
    
    private CmisToken(String cmisToken) {
        this.cmisToken = cmisToken;
    }

}