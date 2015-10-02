package com.altr.core.helper.InternalModel;

import java.util.ArrayList;

public class Subgroup {
    private ArrayList<Group> groups = new ArrayList<Group>();
    private String tabName;
    private boolean isActive = false;
    private String urlSubgroup;
    private boolean flag = false;
    private Integer subgroupType;

    public Subgroup(Group group, String tab){
        groups.add(group);
        tabName = tab;
        urlSubgroup = tab.replaceAll(" ", "_");
    }

    public Subgroup(){

    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getUrlSubgroup() {
        return urlSubgroup;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getSubgroupType() {
        return subgroupType;
    }

    public void setSubgroupType(Integer subgroupType) {
        this.subgroupType = subgroupType;
    }

    @Override
    public String toString() {
        return "Subgroup{" +
                "groups=" + groups +
                ", tabName='" + tabName + '\'' +
                ", isActive=" + isActive +
                ", urlSubgroup='" + urlSubgroup + '\'' +
                '}';
    }
}
