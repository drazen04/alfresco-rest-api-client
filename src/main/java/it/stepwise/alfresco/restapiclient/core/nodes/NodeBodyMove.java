package it.stepwise.alfresco.restapiclient.core.nodes;

import it.stepwise.alfresco.restapiclient.InputBody;

public class NodeBodyMove extends InputBody {

    private final String targetParentId;
    private String name;

    public NodeBodyMove(String targetParentId) {
        this.targetParentId = targetParentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NodeMoveBody{" +
                "targetParentId='" + targetParentId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
