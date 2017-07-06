package com.elp.model;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by ASUS on 2017/7/5.
 */

public class Relation{
    private String oaId;
    private String obId;

    public Relation(){};
    public Relation(String oaId,String obId){
        this.oaId = oaId;
        this.obId = obId;
    }
    public String getOaId() {
        return oaId;
    }

    public void setOaId(String oaId) {
        this.oaId = oaId;
    }

    public String getObId() {
        return obId;
    }

    public void setObId(String obId) {
        this.obId = obId;
    }
}
