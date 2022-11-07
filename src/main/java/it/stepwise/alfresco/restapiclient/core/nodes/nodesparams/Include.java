package it.stepwise.alfresco.restapiclient.core.nodes.nodesparams;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "include")
public enum Include {
    @JsonProperty("allowableOperations")
    ALLOWABLE_OPERATIONS("allowableOperations"),
    @JsonProperty("association")
    ASSOCIATION("association"),
    @JsonProperty("isLink")
    IS_LINK("isLink"),
    @JsonProperty("isFavorite")
    IS_FAVORITE("isFavorite"),
    @JsonProperty("isLocked")
    IS_LOCKED("isLocked"),
    @JsonProperty("path")
    PATH("path"),
    @JsonProperty("permissions")
    PERMISSIONS("permissions"),
    @JsonProperty("definition")
    DEFINITION("definition");

    public final String value;

    private Include(String value) {
        this.value = value;
    }
}
