package it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity;

import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.enums.Operator;

import org.apache.commons.lang3.StringUtils;

import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * 
 * <h1>CMISCondition.java</h1>
 *
 * <p>
 * </p>
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author Daniele Del Vecchio
 * @lastUpdate 2022-12-19 - Daniele Del Vecchio
 */
public class CMISCondition {

	public String field = StringUtils.EMPTY;
	public String value = StringUtils.EMPTY;
	public static String tableName = StringUtils.EMPTY;
	
	public Operator operator;

	public CMISCondition(String field, String value, Operator operator) {
		this.field = field;
		this.value = value;
		this.operator = operator;
	}
	
	public CMISCondition fromTable(String tableName) {
		CMISCondition.tableName = tableName;
		return this;
	}

	private static Predicate<String> hasValue = (value) -> StringUtils.isNotBlank(value);
	
	private static BiFunction<String, String, String> equalsFunction = (field, value) ->
		(hasValue.test(CMISCondition.tableName) ? CMISCondition.tableName + "." : "")
			+ field
            + Operator.EQUALS.operatorValue
            + "'"
            + value
            + "'";
	private static BiFunction<String, String, String> notEqualsFunction = (field, value) ->
		(hasValue.test(CMISCondition.tableName) ? CMISCondition.tableName + "." : "")
			+ field
            + Operator.NOT_EQUALS.operatorValue
            + "'"
            + value
            + "'";

    public String getStringValue() {
        switch(this.operator) {
            case EQUALS     :   return EQUALS(this.field, this.value);
            case NOT_EQUALS :   return NOT_EQUALS(this.field, this.value);
            default         :   return "";
        }
    }
    
    public static String ConditionFunction(String field, String value, BiFunction<String, String, String> biFunction) {
        return biFunction.apply(field, value);
    }

    public static String EQUALS(String field, String value) {
        return ConditionFunction(field, value, equalsFunction);
    }

    public static String NOT_EQUALS(String field, String value) {
        return ConditionFunction(field, value, notEqualsFunction);
    }
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

}