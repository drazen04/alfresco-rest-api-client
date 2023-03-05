package it.stepwise.alfresco.restapiclient.core.nodes;

import org.json.JSONArray;
import org.json.JSONObject;

public class BodyResponse {

    private final JSONObject jsonObject;

    public BodyResponse(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }


    public JSONObject getList() {
        return this.jsonObject.getJSONObject("list");
    }

    public JSONArray getEntries() {
        return getList().getJSONArray("entries");
    }

    public JSONObject getEntryAtIndex(int index) {
        if (getEntries().length() == 0) return new JSONObject();
        return getEntries().getJSONObject(index).getJSONObject("entry");
    }

    public boolean hasEntries() {
        return getEntries().length() != 0;
    }


    public JSONObject getFirstEntry() {
        return getEntryAtIndex(0);
    }

    public JSONObject getEntry() {
        return this.jsonObject.getJSONObject("entry");
    }
}
