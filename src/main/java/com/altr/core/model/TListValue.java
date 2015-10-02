package com.altr.core.model;

import javax.persistence.*;

@Entity
@Table(name = "t_list_values")
public class TListValue
{
    @Id
    @Column(name = "list_value_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer listValueId;
    private String value;

    @ManyToOne
    @JoinColumn(name = "attr_type_def_id")
    private TAttrDef tAttrDef;

    @Override
    public String toString()
    {
        return "TListValue{" +
            "listValueId=" + listValueId +
            ", name='" + value + '\'' +
            '}';
    }

    public Integer getListValueId()
    {
        return listValueId;
    }

    public void setListValueId(Integer listValueId)
    {
        this.listValueId = listValueId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TAttrDef gettAttrDef() {
        return tAttrDef;
    }

    public void settAttrDef(TAttrDef tAttrDef) {
        this.tAttrDef = tAttrDef;
    }
}
