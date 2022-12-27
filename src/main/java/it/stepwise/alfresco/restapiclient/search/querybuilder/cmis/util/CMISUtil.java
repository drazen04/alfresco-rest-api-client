package it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.util;

import org.apache.commons.lang3.StringUtils;

import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.bean.Pair;

/**
 * 
 * <h1>CMISUtil.java</h1>
 *
 * <p>
 * </p>
 *
 * @version 1.0.1
 * @since 1.0.1
 * @author Daniele Del Vecchio
 * @lastUpdate 2022-12-27 - Daniele Del Vecchio
 */
public class CMISUtil {
	
	/**
	 * 
	 * <h1>getSecondStatement</h1>
	 *
	 * <p>
	 * </p>
	 *
	 * @param statement
	 * @return
	 *
	 * @version 1.0.0
     * @since 1.0.1
	 * @author Daniele Del Vecchio
	 * @lastUpdate 2022-12-21 - Daniele Del Vecchio
	 */
	public static String getSecondStatementIfExist(Pair<String, String> statement) {
       return StringUtils.isNotBlank(statement.getSecond()) ? statement.getSecond() : statement.getFirst();
	}
	
}