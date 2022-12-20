package it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.enums;

/**
 * 
 * <h1>Operator.java</h1>
 *
 * <p>
 * </p>
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author Daniele Del Vecchio
 * @lastUpdate 2022-12-15 - Daniele Del Vecchio
 */
public enum Operator {

    EQUALS(" = "),
    NOT_EQUALS(" != ");
    
    public final String operatorValue;
    
    private Operator(String operatorValue) {
        this.operatorValue = operatorValue;
    }
    
}