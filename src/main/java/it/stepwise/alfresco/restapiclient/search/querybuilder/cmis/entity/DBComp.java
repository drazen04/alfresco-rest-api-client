package it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 
 * <h1>DBComponent.java</h1>
 *
 * <p>
 * </p>
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author Daniele Del Vecchio
 * @lastUpdate 2022-12-19 - Daniele Del Vecchio
 */
public class DBComp {

	private String entity = StringUtils.EMPTY;
	private String alias = StringUtils.EMPTY;
	private String entityAsAlias = StringUtils.EMPTY;

	private Function<String, String> composeAliasFunction = (alias) -> alias;

	private BiFunction<String, String, String> composeAliasBiFunction = (field, alias) -> field + " as " + alias + "";

	private Predicate<String> selectAllPredicate = (value) -> value.equalsIgnoreCase("*");
	private Predicate<String> notSelectAllPredicate = (value) -> !this.selectAllPredicate.test(value);

	public DBComp(String entity, String alias) {
		this.entity = entity;
		this.alias = alias;
		this.entityAsAlias = this.composeAlias();
	}

	public DBComp(String entity) {
		this.entity = entity;
	}

	public DBComp aka(String alias) {
		this.alias = alias;
		this.entityAsAlias = this.composeAlias();
		return this;
	}

	private String composeAlias() {
		return this.isNotSelectAll() ? this.composeAliasFunction(this.entity, this.alias) : this.aliasFunction(this.entity);
	}

	private Boolean isNotSelectAll() {
		return this.notSelectAllPredicate.test(this.entity);
	}

	@SuppressWarnings("unused")
	private Boolean isSelectAll() {
		return this.selectAllPredicate.test(this.entity);
	}

	private String aliasFunction(String column) {
		return this.composeAliasFunction.apply(column);
	}

	private String composeAliasFunction(String column, String value) {
		return this.composeAliasBiFunction.apply(column, value);
	}

	public String getEntity() {
		return this.entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getAlias() {
		return StringUtils.isEmpty(this.alias) ? "" : this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getEntityAsAlias() {
		return entityAsAlias;
	}

	public void setEntityAsAlias(String entityAsAlias) {
		this.entityAsAlias = entityAsAlias;
	}

}