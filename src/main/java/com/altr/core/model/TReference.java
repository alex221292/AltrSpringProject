package com.altr.core.model;

import javax.persistence.*;

@Entity
@Table(name = "t_references")
@AssociationOverrides({
        @AssociationOverride(name = "id.attrId", joinColumns = @JoinColumn(name = "attr_id")),
        @AssociationOverride(name = "id.objectId", joinColumns = @JoinColumn(name = "object_id")),
        @AssociationOverride(name = "id.reference", joinColumns = @JoinColumn(name = "reference"))
})
public class TReference {

    @Id
    private TReferencePK id;

    public TReferencePK getId() {
        return id;
    }

    public void setId(TReferencePK id) {
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

    @Transient
    public int getReference() {
        return getId().getReference();
    }

    public void setReference(int reference) {
        getId().setReference(reference);
    }

    @Override
    public String toString() {
        return "TReference{" +
                "id=" + id +
                '}';
    }
}
