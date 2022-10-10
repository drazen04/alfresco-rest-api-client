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


    public enum Type{
        ALLOW_OWNER_CHANGES("ALLOW_OWNER_CHANGES"),
        FULL("FULL");

        public final String value;

        private Type(String value) {
            this.value = value;
        }
    }

    public enum Lifetime {
        EPHEMERAL("EPHEMERAL"),
        PERSISTENT("PERSISTENT");

        public final String value;

        private Lifetime(String value) {
            this.value = value;
        }
    }
}
