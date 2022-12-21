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
 * @version 1.0.0
 * @since 1.0.1
 * @author Daniele Del Vecchio
 * @lastUpdate 2022-12-21 - Daniele Del Vecchio
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
       return StringUtils.isNotBlank(getSecondPairObject(statement)) ? getSecondPairObject(statement) : getFirstPairObject(statement);
	}
	
	/**
	 * 
	 * <h1>getFirstPairObject</h1>
	 *
	 * <p>
	 * </p>
	 *
	 * @param pair
	 * @return
	 *
	 * @version 1.0.0
     * @since 1.0.1
	 * @author Daniele Del Vecchio
	 * @lastUpdate 2022-12-21 - Daniele Del Vecchio
	 */
    private static String getFirstPairObject(Pair<String, String> pair) {
        return pair.getFirst();
    }

    /**
     * 
     * <h1>getSecondPairObject</h1>
     *
     * <p>
     * </p>
     *
     * @param pair
     * @return
     *
     * @version 1.0.0
     * @since 1.0.1
     * @author Daniele Del Vecchio
     * @lastUpdate 2022-12-21 - Daniele Del Vecchio
     */
    private static String getSecondPairObject(Pair<String, String> pair) {
        return pair.getSecond();
    }
	
}