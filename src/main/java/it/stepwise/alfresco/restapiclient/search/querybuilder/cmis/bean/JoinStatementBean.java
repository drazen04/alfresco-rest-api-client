package it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.bean;

import org.apache.commons.lang3.StringUtils;

import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity.DBComp;

/**
 * 
 * <h1>JoinStatementBean.java</h1>
 *
 * <p>
 * </p>
 *
 * @version 1.0.0
 * @since 1.0.1
 * @author Daniele Del Vecchio
 * @lastUpdate 2022-12-21 - Daniele Del Vecchio
 */
public class JoinStatementBean {

	public DBComp join = null;
	public String columnJoin = StringUtils.EMPTY;

	public JoinStatementBean() {
	}
	
	public JoinStatementBean(DBComp join, String columnJoin) {
		this.join = join;
		this.columnJoin = columnJoin;
	}

	public static JoinStatementBean composeJoinBean(DBComp join, String column) {
		DBComp dbComp = new DBComp(join.getKey(), join.getAlias());
    	return new JoinStatementBean(dbComp, column);
	}
	
	public static String getKeyAsAliasIfExist(DBComp join) {
		return StringUtils.isNotBlank(join.getKeyAsAlias()) ? join.getKeyAsAlias() : join.getKey();
	}
	
	public static String getAliasIfExist(DBComp join) {
		return StringUtils.isNotBlank(join.getAlias()) ? join.getAlias() : join.getKey();
	}

	public DBComp getJoin() {
		return join;
	}

	public void setJoin(DBComp join) {
		this.join = join;
	}

	public String getColumnJoin() {
		return columnJoin;
	}

	public void setColumnJoin(String columnJoin) {
		this.columnJoin = columnJoin;
	}

	@Override
	public String toString() {
		return "JoinStatementBean [join=" + this.join.toString() + ", columnJoin=" + this.columnJoin + "]";
	}

}