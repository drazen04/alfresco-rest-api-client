package it.stepwise.alfresco.restapiclient.search.querybuilder;

import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.CMISAlfresco;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.CMISStrict;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity.CMISCondition;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity.DBComp;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.enums.Type;

public abstract class CMIS implements QueryBuilder {

    public static CMIS withType(Type type) {
        switch(type) {
            case CMIS_STRICT:       return new CMISStrict();
            case CMIS_ALFRESCO:     return new CMISAlfresco();
            default:                return new CMISAlfresco();
        }
    }

    public abstract CMIS SELECT(DBComp... selectStatement);
    public abstract CMIS FROM(DBComp fromStatement);
    public abstract CMIS JOIN(DBComp joinStatement, String columnToJoin);
    public abstract CMIS JOIN_OBJECTID(DBComp joinStatement);
    public abstract CMIS WHERE(CMISCondition condition);
    public abstract CMIS AND(CMISCondition andCondition);
    public abstract CMIS OR(CMISCondition orCondition);

}