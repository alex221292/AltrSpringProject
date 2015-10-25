package com.altr.core.helper.InternalModel;

import com.altr.core.model.TObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private static final Logger logger = LoggerFactory.getLogger(Group.class);

    private String name;
    private String subgroup;
    private String urlSubgroup;
    private boolean isActive = false;
    private Integer showOrder;
    private Integer groupId;
    private Integer flag;
    private List<TObject> tObjects = new ArrayList<TObject>();
    private List<Attribute> attributes = new ArrayList<Attribute>();
    private ArrayList<Button> buttons = new ArrayList<Button>();

    public Group() {

    }

    public Group(String[] args) {
        int arrSize = args.length;
        if (arrSize > 0) {
            this.subgroup = args[0];
            this.urlSubgroup = this.subgroup.replaceAll(" ", "_");
            if (arrSize > 1) {
                this.name = args[1];
                if (arrSize > 2) {
                    this.groupId = Integer.parseInt(args[2]);
                    if (arrSize>3) {
                        this.flag = Integer.parseInt(args[3]);
                        if (arrSize>4) this.name = args[4];
                    }
                }
            }
        }
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlSubgroup() {
        return urlSubgroup;
    }

    public void setUrlSubgroup(String urlSubgroup) {
        this.urlSubgroup = urlSubgroup;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public List<TObject> gettObjects() {
        return tObjects;
    }

    public void settObjects(List<TObject> tObjects) {
        this.tObjects = tObjects;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<Button> buttons) {
        this.buttons = buttons;
    }
}
