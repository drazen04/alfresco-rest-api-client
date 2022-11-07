package it.stepwise.alfresco.restapiclient.search.searchparams;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "sort")
public class Sort {
    
    private String type; // usare enum? - capire altri stati oltre FIELD (forse "RANGE"?)
    private String field;
    private Boolean ascending;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Boolean getAscending() {
        return ascending;
    }

    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }

    public Sort(String type, String field, Boolean ascending) {
        this.type = type;
        this.field = field;
        this.ascending = ascending;
    }

    public Sort(String type, String field) {
        this.type = type;
        this.field = field;
        this.ascending = true;
    }

    public Sort(String field) {
        this.type = "FIELD";
        this.field = field;
        this.ascending = true;
    }
    
}
