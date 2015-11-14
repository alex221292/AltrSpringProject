package com.altr.core.service;

import com.altr.core.helper.InternalModel.Group;
import com.altr.core.model.*;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;


public interface TObjectService
{
    public TObject getObjectById(int objectId);
    public List<TObject> getChildrenObjectsByParentId(int id);
    public List<Group> getGroups(int id);
    public TAttribute getAttributeById(int attrId);
    public TAttrGroup getAttGroupById(int groupId);
    public TParam getParamByObjectAndAttr(int object, int attribute);
    public TListValue getListValueById(int lvId);
    public void updateParam(int objectId, int attrId, String value) throws Exception;
    public void updateParamBulk(int objectId, Map<String, String> updateParam);
    public void deleteObjectBulk(Map<String, String> deleteParams, Integer attrId);
    public boolean createObject(String name, Integer parentId, Integer objectTypeId);
    public TObject getCurrentUser(String name);
}
