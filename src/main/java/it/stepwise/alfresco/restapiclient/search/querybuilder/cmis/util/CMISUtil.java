package it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.bean.JoinStatementBean;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.bean.Pair;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity.DBComp;

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
	 * <h1>composeJoinBean</h1>
	 *
	 * <p>
	 * </p>
	 *
	 * @param joinStatement
	 * @param column
	 * @return
	 *
	 * @version 1.0.0
     * @since 1.0.1
	 * @author Daniele Del Vecchio
	 * @lastUpdate 2022-12-21 - Daniele Del Vecchio
	 */
	public static JoinStatementBean composeJoinBean(DBComp joinStatement, String column) {
    	Map<String, String> join = new HashMap<String, String>();
    	join.put(joinStatement.getEntity(), joinStatement.getAlias());
    	return new JoinStatementBean(join, column);
	}
	
	/**
	 * 
	 * <h1>getAlias</h1>
	 *
	 * <p>
	 * </p>
	 *
	 * @param dbComp
	 * @return
	 *
	 * @version 1.0.0
     * @since 1.0.1
	 * @author Daniele Del Vecchio
	 * @lastUpdate 2022-12-21 - Daniele Del Vecchio
	 */
	public static String getAlias(DBComp dbComp) {
		return StringUtils.isNotBlank(dbComp.getAlias()) ? dbComp.getAlias() : dbComp.getEntity();
	}
	
	/**
	 * 
	 * <h1>getEntityAsAlias</h1>
	 *
	 * <p>
	 * </p>
	 *
	 * @param dbComp
	 * @return
	 *
	 * @version 1.0.0
     * @since 1.0.1
	 * @author Daniele Del Vecchio
	 * @lastUpdate 2022-12-21 - Daniele Del Vecchio
	 */
	public static String getEntityAsAlias(DBComp dbComp) {
		return StringUtils.isNotBlank(dbComp.getEntityAsAlias()) ? dbComp.getEntityAsAlias() : dbComp.getEntity();
	}
	
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
	public static String getSecondStatement(Pair<String, String> statement) {
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

    /**
     * 
     * <h1>setPairObject</h1>
     *
     * <p>
     * </p>
     *
     * @param dbComp
     * @return
     *
     * @version 1.0.0
     * @since 1.0.0
     * @author Daniele Del Vecchio
     * @lastUpdate 2022-12-12 - Daniele Del Vecchio
     */
    public static Pair<String, String> setPairObject(DBComp dbComp) {
        return new Pair<String, String>(dbComp.getEntity(), dbComp.getAlias());
    }
	
}