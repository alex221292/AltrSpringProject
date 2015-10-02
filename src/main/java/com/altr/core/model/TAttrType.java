package com.altr.core.model;


import javax.persistence.*;

@Entity
@Table(name = "t_attr_types")
public class TAttrType
{
    @Id
    @Column(name = "attr_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attrTypeId;

    private String name;

    public Integer getAttrTypeId()
    {
        return attrTypeId;
    }

    public void setAttrTypeId(Integer attrTypeId)
    {
        this.attrTypeId = attrTypeId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "TAttrTypes{" +
            "attrTypeId=" + attrTypeId +
            ", name='" + name + '\'' +
            '}';
    }
}
