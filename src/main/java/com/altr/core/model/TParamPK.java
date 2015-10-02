package com.altr.core.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class TParamPK implements Serializable
{
    private static final long serialVersionUID = 3543236089104499992L;
    @Column(name = "attr_id")
    private Integer attrId;
    @Column(name = "object_id")
    private Integer objectId;

    public TParamPK() {
    }

    public TParamPK(Integer attrId, Integer objectId) {
        this.attrId = attrId;
        this.objectId = objectId;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TParamPK tParamPK = (TParamPK) o;

        if (attrId != null ? !attrId.equals(tParamPK.attrId) : tParamPK.attrId != null) return false;
        if (objectId != null ? !objectId.equals(tParamPK.objectId) : tParamPK.objectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attrId != null ? attrId.hashCode() : 0;
        result = 31 * result + (objectId != null ? objectId.hashCode() : 0);
        return result;
    }
}
