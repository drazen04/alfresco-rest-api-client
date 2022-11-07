package it.stepwise.alfresco.restapiclient.search;

import it.stepwise.alfresco.restapiclient.InputBody;
import it.stepwise.alfresco.restapiclient.search.searchparams.Include;
import it.stepwise.alfresco.restapiclient.search.searchparams.Paging;
import it.stepwise.alfresco.restapiclient.search.searchparams.Query;
import it.stepwise.alfresco.restapiclient.search.searchparams.Sort;

public class SearchBody extends InputBody {
    
    private Query query;
    private Paging paging;
    private Include[] include;
    // private Boolean includeRequest;
    // private Fields fields;
    private Sort[] sort;
    // private Templates templates;
    // private Defaults defaults;
    // private Localization localization;
    // private FilterQueries filterQueries;
    // private FacetQueries facetQueries;
    // private FacetFields facetFields;
    // private FacetIntervals facetIntervals;
    // private Pivots pivots;
    // private Stats stats;
    // private Spellcheck spellcheck;
    // private Scope scope;
    // private Limits limits;
    // private Highlight highlight;
    // private Ranges ranges;

    public Query getQuery() {
        return query;
    }
    public Paging getPaging() {
        return paging;
    }
    public Include[] getInclude() {
        return include;
    }
    public Sort[] getSort() {
        return sort;
    }
    public void setQuery(Query query) {
        this.query = query;
    }
    public void setPaging(Paging paging) {
        this.paging = paging;
    }
    public void setInclude(Include[] include) {
        this.include = include;
    }
    public void setSort(Sort[] sort) {
        this.sort = sort;
    }

}
