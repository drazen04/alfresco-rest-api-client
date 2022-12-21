package it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.bean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

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

	public Map<String, String> join = new HashMap<String, String>();
	public String columnJoin = StringUtils.EMPTY;

	public JoinStatementBean() {
	}
	
	public JoinStatementBean(Map<String, String> join, String columnJoin) {
		this.join = join;
		this.columnJoin = columnJoin;
	}

	public Map<String, String> getJoin() {
		return join;
	}

	public void setJoin(Map<String, String> join) {
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
		return "JoinStatementBean [join=" + this.join + ", columnJoin=" + this.columnJoin + "]";
	}

}