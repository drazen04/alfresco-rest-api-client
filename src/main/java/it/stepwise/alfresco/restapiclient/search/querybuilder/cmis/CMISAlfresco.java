package it.stepwise.alfresco.restapiclient.search.querybuilder.cmis;

import it.stepwise.alfresco.restapiclient.search.querybuilder.CMIS;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.bean.Pair;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.constants.CMISConstant;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity.CMISCondition;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity.DBComp;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.enums.CmisToken;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.enums.Conditions;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.enums.Operator;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <h1>CMISAlfresco.java</h1>
 *
 * <p>
 * </p>
 *
 * @author Daniele Del Vecchio
 * @version 1.0.0
 * @lastUpdate 2022-12-19 - Daniele Del Vecchio
 * @since 1.0.0
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
                        .collect(Collectors.toMap(DBComp::getEntity, DBComp::getAlias))
                );

        this.cmisQuery.setStringValue
                (CmisToken.SELECT
                        + " "
                        + Arrays.asList(selectFields)
                        .stream()
                        .map(field -> StringUtils.isBlank
                                (field.getEntityAsAlias())
                                ? field.getEntity()
                                : field.getEntityAsAlias()
                        )
                        .collect(Collectors.joining(", "))
                );

        return this;
    }

    @Override
    public CMIS FROM(DBComp fromStatement) {
        this.cmisQuery.setFromStatement(this.setPairObject(fromStatement));

        String entity = StringUtils.isBlank(fromStatement.getEntityAsAlias())
                ? fromStatement.getEntity()
                : fromStatement.getEntityAsAlias();

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
        Pair<Pair<String, String>, String> pair = new Pair<Pair<String, String>, String>(this.setPairObject(joinStatement), columnToJoin);

        this.cmisQuery.setJoinStatement(pair);

        String entity = StringUtils.isBlank(joinStatement.getEntityAsAlias())
                ? joinStatement.getEntity()
                : joinStatement.getEntityAsAlias();

        String entityAsAlias = StringUtils.isBlank(joinStatement.getEntityAsAlias())
                ? joinStatement.getEntity()
                : joinStatement.getEntityAsAlias();

        String fromStatement = StringUtils.isNotBlank
                (this.getSecondPairObject(this.cmisQuery.getFromStatement()))
                ? this.getSecondPairObject(this.cmisQuery.getFromStatement())
                : this.getFirstPairObject(this.cmisQuery.getFromStatement()
        );

        this.cmisQuery.setStringValue
                (this.cmisQuery.getStringValue()
                        + " "
                        + CmisToken.JOIN
                        + " "
                        + entity
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
        this.cmisQuery.setJoinObjectId(this.setPairObject(joinStatement));

        String entity = StringUtils.isBlank(joinStatement.getAlias())
                ? joinStatement.getEntity()
                : joinStatement.getAlias();

        String entityAsAlias = StringUtils.isBlank(joinStatement.getEntityAsAlias())
                ? joinStatement.getEntity()
                : joinStatement.getEntityAsAlias();

        String fromStatement = StringUtils.isNotBlank
                (this.getSecondPairObject(this.cmisQuery.getFromStatement()))
                ? this.getSecondPairObject(this.cmisQuery.getFromStatement())
                : this.getFirstPairObject(this.cmisQuery.getFromStatement()
        );

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
                        + entity
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

    @SuppressWarnings("unused")
    private String getFirstPairObject(Pair<String, String> pair) {
        return pair.getFirst();
    }

    private String getSecondPairObject(Pair<String, String> pair) {
        return pair.getSecond();
    }

    /**
     * <h1>setPairObject</h1>
     *
     * <p>
     * </p>
     *
     * @param cmisTable
     * @return
     * @version 1.0.0
     * @author Daniele Del Vecchio
     * @lastUpdate 2022-12-12 - Daniele Del Vecchio
     * @since 1.0.0
     */
    private Pair<String, String> setPairObject(DBComp cmisTable) {
        return new Pair<String, String>(cmisTable.getEntity(), cmisTable.getAlias());
    }

}