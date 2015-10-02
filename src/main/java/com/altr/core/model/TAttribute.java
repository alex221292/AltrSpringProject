package com.altr.core.model;


import javax.persistence.*;

@Entity
@Table(name = "t_attributes")
public class TAttribute
{
    @Id
    @Column(name = "attr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attrId;

    @ManyToOne
    @JoinColumn(name = "attr_type_id")
    private TAttrType tAttrType;

    @ManyToOne
    @JoinColumn(name = "attr_group_id")
    private TAttrGroup tAttrGroup;

    @ManyToOne
    @JoinColumn(name = "attr_type_def_id")
    private TAttrDef tAttrDef;

    private String name;
    private String properties;
    private String description;
    private String flags;

    @Column(name="show_order")
    private Integer showOrder;


    public Integer getAttrId()
    {
        return attrId;
    }

    public void setAttrId(Integer attrId)
    {
        this.attrId = attrId;
    }

    public TAttrType gettAttrType()
    {
        return tAttrType;
    }

    public void settAttrType(TAttrType tAttrTypes)
    {
        this.tAttrType = tAttrType;
    }

    public TAttrGroup gettAttrGroup()
    {
        return tAttrGroup;
    }

    public void settAttrGroup(TAttrGroup tAttrGroup)
    {
        this.tAttrGroup = tAttrGroup;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getProperties()
    {
        return properties;
    }

    public void setProperties(String properties)
    {
        this.properties = properties;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public TAttrDef gettAttrDef() {
        return tAttrDef;
    }

    public void settAttrDef(TAttrDef tAttrDef) {
        this.tAttrDef = tAttrDef;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    @Override
    public String toString()
    {
        return "TAttributes{" +
            "attrId=" + attrId +
            ", tAttrTypes=" + tAttrType +
            ", tAttrGroups=" + tAttrGroup +
            ", name='" + name + '\'' +
            ", properties='" + properties + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
