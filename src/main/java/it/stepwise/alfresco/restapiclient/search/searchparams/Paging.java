package it.stepwise.alfresco.restapiclient.search.searchparams;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "paging")
public class Paging {
    
    private Integer maxItems;
    @JsonInclude(Include.NON_NULL)
    private Integer skipCount;

    public Integer getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(Integer maxItems) {
        this.maxItems = maxItems;
    }

    public Integer getSkipCount() {
        return skipCount;
    }

    public void setSkipCount(Integer skipCount) {
        this.skipCount = skipCount;
    }

    public Paging(Integer maxItems, Integer skipCount) {
        this.maxItems = maxItems;
        this.skipCount = skipCount;
    }

    public Paging(Integer maxItems) {
        this.maxItems = maxItems;
        this.skipCount = 0;
    }    

}
