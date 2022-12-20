package it.stepwise.alfresco.restapiclient.search.querybuilder;

import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MultiValuedMap;

/**
 * <h2>Fts.java</h2>
 *
 * <p>
 * Utility class for query building.</p>
 * <p><b>!DISCLAIMER!:</b> There's no logic to check the sense of builded queries - Business before pleasure I'm afraid.
 * </p>
 */
public class Fts implements QueryBuilder {

    private String query = "";
	
	public enum Condition {
		OR("OR"),
		AND("AND");
		
		public final String value;
		
		private Condition(String conditionValue) {
			this.value = conditionValue;
		}
	}
	
	
	public static Fts ftsBuilder() {
		return new Fts();
	}
	
	/**
	 * 
	 * <h2>TYPE</h2>
	 *
	 * <p>
	 * Search for a TYPE
	 * </p>
	 *
	 * Fts.ftsBuilder()
	 *		.TYPE("cm:folder")
	 *
	 * @param type
	 */
	public Fts TYPE(String type) {
		this.query += "TYPE:\"" + type + "\"";
		return this;
	}
	
	/**
	 * <h2>EXACTTYPE</h2>
	 *
	 * <p>
     * Search for an EXACTTYPE
	 * </p>
	 *
	 * @param type
	 */
	public Fts EXACTTYPE(String type) {
		this.query += "EXACTTYPE:\"" + type + "\"";
		return this;
	}
	
	/**
	 * 
	 * <h2>TYPES</h2>
	 *
	 * <p>Search for multiple TYPE
	 * </p>
	 *
	 * @param types
	 * @param condition
	 */
	public Fts TYPES(Collection<String> types, Condition condition) {

		this.query += this.groupingStatement("TYPE", types, condition);
		
		return this;
	}
	
	/**
	 * 
	 * <h2>PATH</h2>
	 *
	 * <p>Search for a PATH
	 * </p>
	 *
	 * @param path
	 */
	public Fts PATH(String path) {
		this.query += "PATH:\"" + path + "\"";
		return this;
	}
	
	/**
	 * 
	 * <h2>PARENT</h2>
	 *
	 * <p>Search for a PARENT.
     * N.B. PARENT can be used to search only for primary children (1st level).
     * @see ANCESTOR to get a recursive results.
     * </p>
	 *
	 * @param parent
	 *
	 */
	public Fts PARENT(String parent) {
		this.query += "PARENT:\"" + parent + "\"";
		return this;
	}
	
	
	/**
	 * 
	 * <h2>ANCESTOR</h2>
	 *
	 * <p>N.B. ANCESTOR returns all recursive nodes under the ancestor level.
	 * @see PARENT to obtain only primary children nodes (1st level) </p>
	 *
	 * @param ancestor
	 *
	 */
	public Fts ANCESTOR(String ancestor) {
		this.query += "ANCESTOR:\"" + ancestor + "\"";
		return this;
	}
	
	/**
	 * 
	 * <h2>NOT_ASPECTS</h2>
	 *
	 * <p>Search for multiple <b>negated</b> ASPECT 
	 * </p>
	 *
	 * @param aspects
	 * @param condition
	 */
	public Fts NOT_ASPECTS(Collection<String> aspects, Condition condition) {
		
		this.query += this.groupingStatement("!ASPECT", aspects, condition);
		
		return this;
	}
	
	
	/**
	 * 
	 * <h2>ASPECTS</h2>
	 *
	 * <p>Search for multiple ASPECT
	 * </p>
	 *
	 * @param aspects
	 * @param condition

	 */
	public Fts ASPECTS(Collection<String> aspects, Condition condition) {
		
		this.query += this.groupingStatement("ASPECT", aspects, condition);
		
		return this;
	}
	
	
	/**
	 * 
	 * <h2>ASPECT</h2>
	 *
	 * <p>
	 * Search for an ASPECT
	 * </p>
	 * <pre>
	 * Fts.ftsBuilder()
	 *		.TYPE("cm:folder")
	 *		.AND()
	 *		.ASPECT("cm:lockable")
	 * </pre>
	 * 
	 * @param aspect
	 */
	public Fts ASPECT(String aspect) {
		this.query += "ASPECT:\"" + aspect + "\"";
		return this;
	}
	
	
	/**
	 * 
	 * <h2>NOT_ASPECT</h2>
	 *
	 * <p>
	 * Search for a <b>negated</b> ASPECT
	 * </p>
	 * 
	 * <pre>
	 * Fts.ftsBuilder()
	 *		.TYPE("cm:folder")
	 *		.AND()
	 *		.NOT_ASPECT("cm:lockable")
	 * </pre>
	 * @param aspect
	 */
	public Fts NOT_ASPECT(String aspect) {
		this.query += "!ASPECT:\"" + aspect + "\"";
		return this;
	}
	
	/**
	 * 
	 * <h2>OR</h2>
	 *
	 * <p>
	 * Add OR condition between statements
	 * </p>
	 * <pre>
	 * Fts.ftsBuilder()
	 *		.TYPE("cm:content")
	 *		.AND()
	 *		.PROP("cm:name", "doc.txt")
	 *		.OR()
	 *		.PROP("cm:title", "doc-title")
	 * </pre>
	 */
	public Fts OR() {
		this.query += " " + Condition.OR + " ";
		return this;
	}
	
	
	/**
	 * 
	 * <h2>AND</h2>
	 *
	 * <p>
	 * Add AND condition between statements
	 * </p>
	 * 
	 * <pre>
	 * Fts.ftsBuilder()
	 *		.TYPE("cm:content")
	 *		.AND()
	 *		.PROP("cm:name", "doc.txt")
	 * </pre>
	 */
	public Fts AND() {
		this.query += " " + Condition.AND + " ";
		return this;
	}
	
	/**
	 * 
	 * <h2>PROPS</h2>
	 *
	 * <p>
	 * Search for multiple PROP (also with with same key), with OR or AND condition
	 * </p>
	 *
	 * <pre>
	 * Fts.ftsBuilder()
	 *		.PROPS(new ArrayListValuedHashMap<String, Serializable>() {{
	 *	 		put("cm:name", "Example name");
	 *			put("cm:name", "Another name");
	 *		}}, Fts.Condition.OR)
	 * </pre>
	 *
	 * @param map
	 * @param condition
	 */
	public Fts PROPS(MultiValuedMap<String, String> map, Condition condition) {
		
		this.query += "(" + map.entries().stream()
				.map(entry -> entry.getKey() + ":" + entry.getValue())
				.collect(Collectors.joining(" " + condition.value + " ")) + ")";
		
		return this;
	}
	
	/**
	 * 
	 * <h2>NOT_PROPS</h2>
	 *
	 * <p>
	 * Search for multiple <b>negated</b> PROP (also with with same key), with OR or AND condition
	 * </p>
	 *
	 * <pre>
	 * Fts.ftsBuilder()
	 *		.PROPS(new ArrayListValuedHashMap<String, Serializable>() {{
	 *	 		put("cm:name", "Example name");
	 *			put("cm:name", "Another name");
	 *		}}, Fts.Condition.OR)
	 * </pre>
	 *
	 * @param map
	 * @param condition
	 */
	public Fts NOT_PROPS(MultiValuedMap<String, String> map, Condition condition) {
		
		this.query += "!(" + map.entries().stream()
				.map(entry -> entry.getKey() + ":" + entry.getValue())
				.collect(Collectors.joining(" " + condition.value + " ")) + ")";
		
		return this;
	}
	
	/**
	 * 
	 * <h2>PROP</h2>
	 *
	 * <p>
	 * Search for a PROP value
	 * </p>
	 *
	 * @param propKey
	 * @param value
	 */
	public Fts PROP(String propKey, String value) {
		
		this.query += propKey + ":" + this.escapeValue(value);
		
		return this;
	}
	
	/**
	 * 
	 * <h2>NOT_PROP</h2>
	 *
	 * <p>
	 * Search for a <b>negated</b> PROP value
	 * </p>
	 *
	 * @param propKey
	 * @param value
	 */
	public Fts NOT_PROP(String propKey, String value) {
		
		this.query += "!" + propKey + ":" + this.escapeValue(value);
		
		return this;
	}
	
	/**
	 * 
	 * <h2>SITE</h2>
	 *
	 * <p>Search for a SITE
	 * </p>
	 *
	 * @param siteId
	 */
	public Fts SITE(String siteId) {
		
		this.query += "SITE:" + siteId;
		
		return this;
	}

    private String escapeValue(String value) {
        return "\"" + value + "\""; 
    }
	
	private String groupingStatement(String statementType, Collection<String> statementList, Condition condition) {
		
		return statementType + ":(" + statementList.stream()
            .map(aspect -> "\"" + aspect + "\"")
            .collect(Collectors.joining(" "+ condition.value +" ")) + ")";
	}

    @Override
	public String buildQuery() {
		return this.query.trim();
	}
    
}
