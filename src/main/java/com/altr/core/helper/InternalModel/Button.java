package com.altr.core.helper.InternalModel;

public class Button {
    private String name;
    private String command;
    private Integer attrId;

    public Button(String name, String command, Integer attrId) {
        this.name = name;
        this.command = command;
        this.attrId = attrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }
}
