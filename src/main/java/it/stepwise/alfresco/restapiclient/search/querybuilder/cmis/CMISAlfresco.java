package it.stepwise.alfresco.restapiclient.search.querybuilder.cmis;

import it.stepwise.alfresco.restapiclient.search.querybuilder.CMIS;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.bean.JoinStatementBean;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.constants.CMISConstant;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity.CMISCondition;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity.DBComp;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.enums.CmisToken;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.enums.Conditions;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.enums.Operator;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.util.CMISUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 
 * <h1>CMISAlfresco.java</h1>
 *
 * <p>
 * </p>
 *
 * @version 1.0.1
 * @since 1.0.0
 * @author Daniele Del Vecchio
 * @lastUpdate 2022-12-21 - Daniele Del Vecchio
 */
public class CMISAlfresco extends CMIS {

    public CMISQuery cmisQuery = new CMISQuery();

    @Override
    public String buildQuery() {
        return StringUtils.isNotBlank(this.cmisQuery.getStringValue())
                ? this.cmisQuery.getStringValue().trim()
                : StringUtils.EMPTY;
    }

    @Override
    public CMIS SELECT(DBComp... selectFields) {
        this.cmisQuery.setSelectStatement
                (Arrays.asList(selectFields)
                        .stream()
                        .collect(Collectors.toMap(DBComp::getKey, DBComp::getAlias))
                );

        this.cmisQuery.setStringValue
                (CmisToken.SELECT
                        + " "
                        + Arrays.asList(selectFields)
                        .stream()
                        .map(field -> StringUtils.isBlank
                                (field.getKeyAsAlias())
                                ? field.getKey()
                                : field.getKeyAsAlias()
                        )
                        .collect(Collectors.joining(", "))
                );

        return this;
    }

    @Override
    public CMIS FROM(DBComp fromStatement) {
        this.cmisQuery.setFromStatement(DBComp.setPairObject(fromStatement));

        String entity = StringUtils.isBlank(fromStatement.getKeyAsAlias())
                ? fromStatement.getKey()
                : fromStatement.getKeyAsAlias();

        this.cmisQuery.setStringValue
                (this.cmisQuery.getStringValue()
                        + " "
                        + CmisToken.FROM
                        + " "
                        + entity
                );

        return this;
    }

    @Override
    public CMIS JOIN(DBComp joinStatement, String columnToJoin) {
        this.cmisQuery.setJoinStatement(JoinStatementBean.composeJoinBean(joinStatement, columnToJoin));

        String alias = JoinStatementBean.getAliasIfExist(joinStatement);
        String entityAsAlias = JoinStatementBean.getKeyAsAliasIfExist(joinStatement);
        String fromStatement = CMISUtil.getSecondStatementIfExist(this.cmisQuery.getFromStatement());

        this.cmisQuery.setStringValue
                (this.cmisQuery.getStringValue()
                        + " "
                        + CmisToken.JOIN
                        + " "
                        + alias
                        + " "
                        + CmisToken.ON
                        + " "
                        + entityAsAlias
                        + "."
                        + columnToJoin
                        + Operator.EQUALS.operatorValue
                        + fromStatement
                        + "."
                        + columnToJoin
                );

        return this;
    }

    @Override
    public CMIS JOIN_OBJECTID(DBComp joinStatement) {
        this.cmisQuery.setJoinObjectId(DBComp.setPairObject(joinStatement));

        String alias = JoinStatementBean.getAliasIfExist(joinStatement);
        String entityAsAlias = JoinStatementBean.getKeyAsAliasIfExist(joinStatement);
        String fromStatement = CMISUtil.getSecondStatementIfExist(this.cmisQuery.getFromStatement());

        this.cmisQuery.setStringValue
                (this.cmisQuery.getStringValue()
                	+ " "
                        + CmisToken.JOIN
                        + " "
                        + entityAsAlias
                        + " "
                        + CmisToken.ON
                        + " "
                        + fromStatement
                        + "."
                        + CMISConstant.CMIS_OBJECT_ID
                        + Operator.EQUALS.operatorValue
                        + alias
                        + "."
                        + CMISConstant.CMIS_OBJECT_ID
                );

        return this;
    }

    @Override
    public CMIS WHERE(CMISCondition condition) {
        this.cmisQuery.setWhereStatement(condition.getStringValue());

        this.cmisQuery.setStringValue
                (this.cmisQuery.getStringValue()
                        + " "
                        + CmisToken.WHERE
                        + " "
                        + this.cmisQuery.getWhereStatement()
                );

        return this;
    }

    @Override
    public CMIS AND(CMISCondition andCondition) {
        this.cmisQuery.setStringValue
                (this.cmisQuery.getStringValue()
                        + " "
                        + Conditions.AND
                        + " "
                        + andCondition.getStringValue()
                );

        return this;
    }

    @Override
    public CMIS OR(CMISCondition orCondition) {
        this.cmisQuery.setStringValue
                (this.cmisQuery.getStringValue()
                        + " "
                        + Conditions.OR
                        + " "
                        + orCondition.getStringValue()
                );

        return this;
    }

}