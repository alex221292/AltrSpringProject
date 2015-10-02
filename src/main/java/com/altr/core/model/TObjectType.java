package com.altr.core.model;

import javax.persistence.*;

@Entity
@Table(name = "t_object_types")
public class TObjectType
{
    @Id
    @Column(name = "object_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer objectTypeId;

    @Column(name = "parent_type_id")
    private Integer parentObjectTypeId;

    private String name;
    private String description;

    public int getObjectTypeId()
    {
        return objectTypeId;
    }

    public void setObjectTypeId(int objectTypeId)
    {
        this.objectTypeId = objectTypeId;
    }

    public int getParentObjectTypeId()
    {
        return parentObjectTypeId;
    }

    public void setParentObjectTypeId(int parentObjectTypeId)
    {
        this.parentObjectTypeId = parentObjectTypeId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "TObjectType{" +
            "objectTypeId=" + objectTypeId +
            ", parentObjectTypeId=" + parentObjectTypeId +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
