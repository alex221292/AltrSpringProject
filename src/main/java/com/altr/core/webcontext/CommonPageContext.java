package com.altr.core.webcontext;

import com.altr.core.helper.InternalModel.Attribute;
import com.altr.core.helper.InternalModel.Group;
import com.altr.core.helper.InternalModel.Subgroup;
import com.altr.core.model.TAttribute;
import com.altr.core.model.TObject;
import com.altr.core.model.TParam;

import java.util.ArrayList;
import java.util.List;

public interface CommonPageContext
{
    public void getPageData(int id, String tab, String mode);
    public TObject gettObject();
    public List<Group> getGroups();
    public String getError();
    public String getSubgroup();
    public List<Subgroup> getSubgroups();
    public Subgroup getExternalActiveSubgroup();
    public String getTest();
}
