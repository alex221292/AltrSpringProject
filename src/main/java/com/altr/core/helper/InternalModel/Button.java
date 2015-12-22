package com.altr.core.helper.InternalModel;

public class Button {
    private String name;
    private Integer attrId;

    public Button(String name, Integer attrId) {
        this.name = name;
        this.attrId = attrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }
}
