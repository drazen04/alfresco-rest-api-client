package it.stepwise.alfresco.restapiclient.search.querybuilder.cmis;

import it.stepwise.alfresco.restapiclient.search.querybuilder.CMIS;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity.CMISCondition;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity.DBComp;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * <h1>CMISStrict.java</h1>
 *
 * <p>
 * </p>
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author Daniele Del Vecchio
 * @lastUpdate 2022-12-19 - Daniele Del Vecchio
 */
public class CMISStrict extends CMIS {

	public String query = StringUtils.EMPTY;

	@Override
	public String buildQuery() {
		return null;
	}

	@Override
	public CMIS SELECT(DBComp... selectStatement) {
		return null;
	}

	@Override
	public CMIS FROM(DBComp fromStatement) {
		return null;

	}

	@Override
	public CMIS JOIN(DBComp joinStatement, String columnToJoin) {
		return null;
	}

	@Override
	public CMIS JOIN_OBJECTID(DBComp joinStatement) {
		return null;
	}

	@Override
	public CMIS WHERE(CMISCondition whereCondition) {
		return null;
	}

	@Override
	public CMIS AND(CMISCondition andCondition) {
		return null;
	}

	@Override
	public CMIS OR(CMISCondition orCondition) {
		return null;
	}

}