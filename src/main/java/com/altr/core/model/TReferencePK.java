package com.altr.core.model;

import javax.persistence.Column;

public class TReferencePK {
    private static final long serialVersionUID = 3543236089104499992L;
    @Column(name = "attr_id")
    private Integer attrId;
    @Column(name = "object_id")
    private Integer objectId;
    @Column(name = "reference")
    private Integer reference;

    public TReferencePK() {
    }

    public TReferencePK(Integer attrId, Integer objectId, Integer reference) {
        this.attrId = attrId;
        this.objectId = objectId;
        this.reference = reference;
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

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TReferencePK that = (TReferencePK) o;

        if (attrId != null ? !attrId.equals(that.attrId) : that.attrId != null) return false;
        if (objectId != null ? !objectId.equals(that.objectId) : that.objectId != null) return false;
        if (reference != null ? !reference.equals(that.reference) : that.reference != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attrId != null ? attrId.hashCode() : 0;
        result = 31 * result + (objectId != null ? objectId.hashCode() : 0);
        result = 31 * result + (reference != null ? reference.hashCode() : 0);
        return result;
    }
}
