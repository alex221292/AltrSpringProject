package com.altr.core.model;


import javax.persistence.*;

@Entity
@Table(name = "t_attr_object_types")
public class TAttrObjectType
{
    @Id
    private TAttrObjectTypePK id;

    public TAttrObjectTypePK getId()
    {
        return id;
    }

    public void setId(TAttrObjectTypePK id)
    {
        this.id = id;
    }
}
