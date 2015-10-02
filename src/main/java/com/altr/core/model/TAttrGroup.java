package com.altr.core.model;


import javax.persistence.*;

@Entity
@Table(name = "t_attr_groups")
public class TAttrGroup
{
    @Id
    @Column(name = "attr_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attrGroupId;

    private String name;
    private String subgroup;
    @Column(name = "show_order")
    private Integer showOrder;
    private Integer flags;

    public Integer getAttrGroupId()
    {
        return attrGroupId;
    }

    public void setAttrGroupId(Integer attrGroupId)
    {
        this.attrGroupId = attrGroupId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSubgroup()
    {
        return subgroup;
    }

    public void setSubgroup(String subgroup)
    {
        this.subgroup = subgroup;
    }

    public Integer getShowOrder()
    {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder)
    {
        this.showOrder = showOrder;
    }

    public Integer getFlags() {
        return flags;
    }

    public void setFlags(Integer flags) {
        this.flags = flags;
    }

    @Override
    public String toString()
    {
        return "TAttrGroups{" +
            "attrGroupId=" + attrGroupId +
            ", name='" + name + '\'' +
            ", subgroup='" + subgroup + '\'' +
            ", showOrder=" + showOrder +
            '}';
    }
}
