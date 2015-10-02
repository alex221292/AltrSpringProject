package com.altr.core.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class TAttrObjectTypePK implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name="attr_id")
    private TAttribute tAttribute;

    @ManyToOne
    @JoinColumn(name="object_type_id")
    private TObjectType tObjectType;

    public TAttrObjectTypePK(TAttribute tAttribute, TObjectType tObjectType)
    {
        this.tAttribute = tAttribute;
        this.tObjectType = tObjectType;
    }

    public TAttribute gettAttribute()
    {
        return tAttribute;
    }

    public void settAttribute(TAttribute tAttribute)
    {
        this.tAttribute = tAttribute;
    }

    public TObjectType gettObjectType()
    {
        return tObjectType;
    }

    public void settObjectType(TObjectType tObjectType)
    {
        this.tObjectType = tObjectType;
    }
}
