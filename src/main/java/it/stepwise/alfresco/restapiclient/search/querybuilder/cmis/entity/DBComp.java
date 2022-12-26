package it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity;

import org.apache.commons.lang3.StringUtils;

import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.bean.Pair;

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

	private String key = StringUtils.EMPTY;
	private String alias = StringUtils.EMPTY;
	private String keyAsAlias = StringUtils.EMPTY;

	private Function<String, String> composeAliasFunction = (alias) -> alias;

	private BiFunction<String, String, String> composeAliasBiFunction = (field, alias) -> field + " as " + alias + "";

	private Predicate<String> selectAllPredicate = (value) -> value.equalsIgnoreCase("*");
	private Predicate<String> notSelectAllPredicate = (value) -> !this.selectAllPredicate.test(value);

	public DBComp(String key, String alias) {
		this.key = key;
		this.alias = alias;
		this.keyAsAlias = this.composeAlias();
	}

	public DBComp(String key) {
		this.key = key;
	}

	public DBComp aka(String alias) {
		this.alias = alias;
		this.keyAsAlias = this.composeAlias();
		return this;
	}
	
    public static Pair<String, String> setPairObject(DBComp dbComp) {
        return new Pair<String, String>(dbComp.getKey(), dbComp.getAlias());
    }

	private String composeAlias() {
		return this.isNotSelectAll() ? this.composeAliasFunction(this.key, this.alias) : this.aliasFunction(this.key);
	}

	private Boolean isNotSelectAll() {
		return this.notSelectAllPredicate.test(this.key);
	}

	@SuppressWarnings("unused")
	private Boolean isSelectAll() {
		return this.selectAllPredicate.test(this.key);
	}

	private String aliasFunction(String column) {
		return this.composeAliasFunction.apply(column);
	}

	private String composeAliasFunction(String column, String value) {
		return this.composeAliasBiFunction.apply(column, value);
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAlias() {
		return StringUtils.isEmpty(this.alias) ? "" : this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getKeyAsAlias() {
		return keyAsAlias;
	}

	public void setKeyAsAlias(String keyAsAlias) {
		this.keyAsAlias = keyAsAlias;
	}

	@Override
	public String toString() {
		return "DBComp [key=" + this.key + ", alias=" + this.alias + ", keyAsAlias=" + this.keyAsAlias + "]";
	}

}