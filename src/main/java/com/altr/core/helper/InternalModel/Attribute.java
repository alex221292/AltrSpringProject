package com.altr.core.helper.InternalModel;

import com.altr.core.helper.TObjectUtils;
import com.altr.core.model.TAttribute;
import com.altr.core.model.TListValue;
import com.altr.core.model.TObject;
import com.altr.core.model.TParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.annotation.Resource;

public class Attribute {
    private static final Logger logger = LoggerFactory.getLogger(Attribute.class);
    private TAttribute attribute;
    private String value;
    private TObject object;
    private TListValue listValue;
    private Integer attrType;
    private boolean isValid = false;
    private boolean isReadOnly = false;

    public Attribute() {
    }

    public TObject getObject() {
        return object;
    }

    public void setObject(TObject object) {
        this.object = object;
    }

    public TAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(TAttribute attribute) {
        this.attribute = attribute;
    }

    public Integer getAttrType() {
        return attrType;
    }

    public void setAttrType(Integer attrType) {
        this.attrType = attrType;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public TListValue getListValue() {
        return listValue;
    }

    public void setListValue(TListValue listValue) {
        this.listValue = listValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public void setReadOnly(boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }
}
