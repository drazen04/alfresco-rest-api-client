package it.stepwise.alfresco.restapiclient.search.searchparams;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "fields")
public enum Fields {
    @JsonProperty("id")
    ID("id"),
    @JsonProperty("name")
    NAME("name"),
    @JsonProperty("search")
    SEARCH("search");

    public final String value;

    private Fields(String value) {
        this.value = value;
    }
}
