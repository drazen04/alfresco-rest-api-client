package it.stepwise.alfresco.restapiclient.core.nodes;

import it.stepwise.alfresco.restapiclient.InputBody;
import org.json.JSONObject;

public class NodeBodyUpdate extends InputBody {

    private String name;
    private String nodeType;
    private JSONObject properties;

    public NodeBodyUpdate(String name, String nodeType) {
        this.name = name;
        this.nodeType = nodeType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public void setProperties(JSONObject properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "NodeBodyUpdate{" +
                "name='" + name + '\'' +
                ", nodeType='" + nodeType + '\'' +
                ", properties='" + properties + '\'' +
                '}';
    }

}