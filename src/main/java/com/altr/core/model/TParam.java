package com.altr.core.model;

import javax.persistence.*;

@Entity
@Table(name = "t_params")
@AssociationOverrides({
        @AssociationOverride(name = "id.attrId", joinColumns = @JoinColumn(name = "attr_id")),
        @AssociationOverride(name = "id.objectId", joinColumns = @JoinColumn(name = "object_id"))
})
public class TParam {

    @Id
    private TParamPK id;

    @ManyToOne
    @JoinColumn(name = "list_value_id")
    private TListValue tListValue;
    private String value;

    public TParamPK getId() {
        return id;
    }

    public void setId(TParamPK id) {
        this.id = id;
    }

    @Transient
    public int getObjectId() {
        return getId().getObjectId();
    }

    public void setObjectId(int objectId) {
        getId().setObjectId(objectId);
    }

    @Transient
    public int getAttrId() {
        return getId().getAttrId();
    }

    public void setAttrId(int attrId) {
        getId().setAttrId(attrId);
    }

    public TListValue gettListValue() {
        return tListValue;
    }

    public void settListValue(TListValue tListValue) {
        this.tListValue = tListValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "TParam{" +
                "id=" +
                ", tListValue=" +
                ", value='" + value + '\'' +
                '}';
    }
}
