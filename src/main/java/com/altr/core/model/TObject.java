package com.altr.core.model;

import javax.persistence.*;

@Entity
@Table(name="t_objects")
public class TObject
{
    @Id
    @Column(name="object_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="parent_id")
    private Integer parentId;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name="object_type_id")
    private TObjectType objectType;

    @OneToOne
    @JoinColumn(name="picture_id")
    private TPicture tPicture;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getParentId()
    {
        return parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
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

    public TObjectType getObjectType()
    {
        return objectType;
    }

    public void setObjectType(TObjectType objectType)
    {
        this.objectType = objectType;
    }

    public TPicture gettPicture()
    {
        return tPicture;
    }

    public void settPicture(TPicture tPicture)
    {
        this.tPicture = tPicture;
    }

    @Override
    public String toString()
    {
        return "TObject{" +
            "id=" + id +
            ", parentId=" + parentId +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", objectType=" + objectType +
            ", tPicture=" + tPicture +
            '}';
    }


}
