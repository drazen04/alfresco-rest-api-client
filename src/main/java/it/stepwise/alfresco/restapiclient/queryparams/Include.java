package it.stepwise.alfresco.restapiclient.queryparams;

public enum Include {
    ASSOCIATION("association"),
    IS_LINK("isLink"),
    IS_FAVORITE("isFavorite"),
    IS_LOCKED("isLocked"),
    PATH("path"),
    PERMISSIONS("permissions"),
    DEFINITION("definition");

    public final String value;

    private Include(String value) {
        this.value = value;
    }
}
