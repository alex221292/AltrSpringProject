package com.altr.core.model;

import javax.persistence.*;

@Entity
@Table(name = "t_attr_type_defs")
public class TAttrDef {
    @Id
    @Column(name = "attr_type_def_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attrDefId;

    @ManyToOne
    @JoinColumn(name="object_type_id")
    private TObjectType objectType;

    private String name;

    @ManyToOne
    @JoinColumn(name = "attr_type_id")
    private TAttrType tAttrType;

    public Integer getAttrDefId() {
        return attrDefId;
    }

    public void setAttrDefId(Integer attrDefId) {
        this.attrDefId = attrDefId;
    }

    public TObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(TObjectType objectType) {
        this.objectType = objectType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TAttrType gettAttrType() {
        return tAttrType;
    }

    public void settAttrType(TAttrType tAttrType) {
        this.tAttrType = tAttrType;
    }

    @Override
    public String toString() {
        return "TAttrDef{" +
                "attrDefId=" + attrDefId +
                ", objectType=" + objectType +
                ", name='" + name + '\'' +
                ", tAttrType=" + tAttrType +
                '}';
    }
}
