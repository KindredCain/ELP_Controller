package com.elp.model;

/**
 * Created by NWJ on 2017/7/6.
 */
public class PagerankEntity {
    private String objectIdA;
    private String objectIdB;

    public PagerankEntity(String objectIdA, String objectIdB) {
        this.objectIdA = objectIdA;
        this.objectIdB = objectIdB;
    }

    public String getObjectIdA() {
        return objectIdA;
    }

    public void setObjectIdA(String objectIdA) {
        this.objectIdA = objectIdA;
    }

    public String getObjectIdB() {
        return objectIdB;
    }

    public void setObjectIdB(String objectIdB) {
        this.objectIdB = objectIdB;
    }
}
