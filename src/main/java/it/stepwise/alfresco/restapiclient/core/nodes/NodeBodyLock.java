package it.stepwise.alfresco.restapiclient.core.nodes;

import it.stepwise.alfresco.restapiclient.InputBody;

public class NodeBodyLock extends InputBody {

    private int timeToExpire;
    private Type type;
    private Lifetime lifetime;

    public NodeBodyLock(int timeToExpire, Type lockType, Lifetime lifeTime) {
        this.timeToExpire = timeToExpire;
        this.type = lockType;
        this.lifetime = lifeTime;
    }

    public enum Type {
        ALLOW_OWNER_CHANGES,
        FULL
    }

    public enum Lifetime {
        EPHEMERAL,
        PERSISTENT
    }

}