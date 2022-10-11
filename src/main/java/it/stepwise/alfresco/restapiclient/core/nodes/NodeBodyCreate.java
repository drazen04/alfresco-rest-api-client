package it.stepwise.alfresco.restapiclient.core.nodes;

import it.stepwise.alfresco.restapiclient.InputBody;
import org.json.JSONObject;

public class NodeBodyCreate extends InputBody {
    private String name;
    private String nodeType;
    private String relativePath;
    private JSONObject properties;


    public NodeBodyCreate(String name, String nodeType) {
        this.name = name;
        this.nodeType = nodeType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public void setProperties(JSONObject properties) {
        this.properties = properties;
    }
}
