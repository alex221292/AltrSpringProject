package com.altr.core.webcontext;

import com.altr.core.helper.CoreTools;
import com.altr.core.helper.InternalModel.Attribute;
import com.altr.core.helper.InternalModel.Button;
import com.altr.core.helper.InternalModel.Group;
import com.altr.core.helper.InternalModel.Subgroup;
import com.altr.core.helper.SystemConstants;
import com.altr.core.model.TAttribute;
import com.altr.core.model.TObject;
import com.altr.core.service.TObjectService;
import com.altr.core.sql.SQLStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CommonPageContextImpl implements CommonPageContext {
    private static final Logger logger = LoggerFactory.getLogger(CommonPageContextImpl.class);

    private TObjectService tObjectService;
    private SQLStatement sqlStatement;

    @Autowired(required = true)
    @Qualifier(value = "tObjectService")
    public void settObjectService(TObjectService ts) {
        this.tObjectService = ts;
    }

    @Autowired(required = true)
    @Qualifier(value = "sqlStatement")
    public void setSqlStatement(SQLStatement st) {
        this.sqlStatement = st;
    }


    private Integer objectId;

    private TObject tObject;
    private List<Group> groups = new ArrayList<Group>();
    private List<Subgroup> subgroups = new ArrayList<Subgroup>();
    private String error;

    private String subgroup;
    private Subgroup externalActiveSubgroup;
    private String test;

    @Override
    @Transactional
    public void getPageData(int id, String tab, String mode) {
        if ("1".equals(mode)){
            prepareCommonPage(id, tab, mode);
        }
        else{
            prepareCommonPage(id, tab, mode);
        }
    }


    private void clearPageData() {
        if (!groups.isEmpty()) groups.clear();
        if (!subgroups.isEmpty()) subgroups.clear();
        if (!("".equals(error))) error = "";
    }

    private void prepareCommonPage(int id, String tab, String mode){
        try {
            clearPageData();
            Subgroup activeSubgroup = new Subgroup();
            objectId = id;
            tObject = tObjectService.getObjectById(id);
            if (tObject == null) {
                externalActiveSubgroup = null;
                throw new RuntimeException("Object with current id not found.");
            }
            groups = tObjectService.getGroups(id);
            if (groups.size() != 0) {
                for (Group group : groups) {
                    if (subgroups.isEmpty()) {
                        subgroups.add(new Subgroup(group, group.getSubgroup()));
                    } else {
                        for (Subgroup subgroup1 : subgroups) {
                            if (group.getSubgroup().equals(subgroup1.getTabName())) {
                                activeSubgroup = subgroup1;
                                activeSubgroup.setFlag(true);
                                break;
                            }
                        }
                        if (activeSubgroup.isFlag()) {
                            activeSubgroup.getGroups().add(group);
                        } else {
                            subgroups.add(new Subgroup(group, group.getSubgroup()));
                        }
                    }
                }
                if (!"empty".equals(tab)) {
                    for (Subgroup subgroup1 : subgroups) {
                        if (tab.equals(subgroup1.getTabName())) {
                            subgroup1.setActive(true);
                            activeSubgroup = subgroup1;
                            subgroup = tab;
                            break;
                        }
                    }
                } else {
                    activeSubgroup = subgroups.get(subgroups.size() - 1);
                    activeSubgroup.setActive(true);
                    subgroup = activeSubgroup.getUrlSubgroup();
                }
                if (!activeSubgroup.isActive()) {
                    activeSubgroup = subgroups.get(subgroups.size() - 1);
                    activeSubgroup.setActive(true);
                    subgroup = activeSubgroup.getUrlSubgroup();
                }
                externalActiveSubgroup = activeSubgroup;
                boolean isInfoSubgroup = true;
                for (Group group : externalActiveSubgroup.getGroups()) {
                    List<TObject> tObjects = new ArrayList<TObject>();
                    Collection<Integer> objectIds = new ArrayList<Integer>();
                    List<Attribute> attributes = new ArrayList<Attribute>();
                    List<Button> buttons = new ArrayList<Button>();
                    Collection<Integer> groupAttributes = new ArrayList<Integer>();
                    groupAttributes = sqlStatement.getIntListBySQL(SystemConstants.SQL.GET_ATTRIBUTES_BY_GROUP_AND_TYPE, new Object[]{group.getGroupId(), tObject.getObjectType().getObjectTypeId()});
                    if (Integer.valueOf(1).equals(group.getFlag())) {
                        isInfoSubgroup = false;
                        objectIds = sqlStatement.getIntListBySQL(SystemConstants.SQL.GET_STRUCTURAL_OBJECTS_BY_GROUP, new Object[]{group.getGroupId()});
                        for (Integer attrId : groupAttributes) {
                            TAttribute tAttribute = tObjectService.getAttributeById(attrId);
                            Integer attrType = tAttribute.gettAttrType().getAttrTypeId();
                            if (CoreTools.isHidden(tAttribute.getFlags())) {
                                continue;
                            }
                            if (Integer.valueOf(7).equals(attrType)){
                                Button button = new Button(tAttribute.getName(), tAttribute.getProperties(), attrId);
                                buttons.add(button);
                            }
                        }
                    } else if ((Integer.valueOf(2).equals(group.getFlag()))) {
                        isInfoSubgroup = false;
                        objectIds = sqlStatement.getIntListBySQL(SystemConstants.SQL.GET_CHILDREN_OBJECTS_BY_GROUP_AND_OBJECT, new Object[]{group.getGroupId(), objectId});
                        for (Integer attrId : groupAttributes) {
                            TAttribute tAttribute = tObjectService.getAttributeById(attrId);
                            Integer attrType = tAttribute.gettAttrType().getAttrTypeId();
                            if (CoreTools.isHidden(tAttribute.getFlags())) {
                                continue;
                            }
                            if (Integer.valueOf(7).equals(attrType)){
                                Button button = new Button(tAttribute.getName(), CoreTools.getButtonCommand(tAttribute.getProperties()), attrId);
                                buttons.add(button);
                            }
                        }
                    } else if ((Integer.valueOf(0).equals(group.getFlag()))) {
                        for (Integer attrId : groupAttributes) {
                            TAttribute tAttribute = tObjectService.getAttributeById(attrId);
                            Integer attrType = tAttribute.gettAttrType().getAttrTypeId();
                            if (CoreTools.isHidden(tAttribute.getFlags())) {
                                continue;
                            }
                            if (Integer.valueOf(7).equals(attrType)){
                                Button button = new Button(tAttribute.getName(), CoreTools.getButtonCommand(tAttribute.getProperties()), attrId);
                                buttons.add(button);
                                continue;
                            }
                            attributes.add(generateAttribute(tAttribute, attrId, attrType));
                        }
                    }
                    for (Integer curObj : objectIds) {
                        tObjects.add(tObjectService.getObjectById(curObj));
                    }
                    group.settObjects(tObjects);
                    group.setAttributes(attributes);
                    group.setButtons(buttons);
                }
                if (isInfoSubgroup) externalActiveSubgroup.setSubgroupType(0);
            } else {
                externalActiveSubgroup = null;
                throw new RuntimeException("Object not valid. <a href='?id=10'>back</a>");
            }
        } catch (Exception e) {
            error = e.getMessage();
        }
    }

    private Attribute generateAttribute(TAttribute tAttribute, Integer attrId, Integer attrType) {
        Attribute attribute = new Attribute();
        attribute.setAttrType(attrType);
        attribute.setAttribute(tAttribute);
        if (Integer.valueOf(1).equals(attrType)) {
            if (CoreTools.isCalculable(tAttribute.getFlags())) {
                attribute.setReadOnly(true);
                String query = CoreTools.getCalculateQuery(tAttribute.getProperties());
                try {
                    if (!"".equals(query))
                        attribute.setValue(sqlStatement.getStringBySQL(query, new Object[]{objectId}));
                }
                catch (Exception e)
                {
                    error = e.getMessage();
                }
            } else {
                attribute.setValue(sqlStatement.getStringBySQL(SystemConstants.SQL.GET_PARAMETER_VALUE_BY_OBJECT_AND_ATTR, new Object[]{objectId, attrId}));
            }
        } else if (Integer.valueOf(6).equals(attrType)) {
            Integer listValueId = 0;
            listValueId = sqlStatement.getIntBySQL(SystemConstants.SQL.GET_PARAMETER_LIST_VALUE_BY_OBJECT_AND_ATTR, new Object[]{objectId, attrId});
            if (!Integer.valueOf(0).equals(listValueId)) {
                attribute.setValid(true);
                attribute.setListValue(tObjectService.getListValueById(listValueId));
            }
        }

        return attribute;
    }

    public Integer getObjectId() {
        return objectId;
    }


    @Override
    public TObject gettObject() {
        return tObject;
    }


    @Override
    public List<Group> getGroups() {
        return groups;
    }


    @Override
    public String getError() {
        return error;
    }

    @Override
    public String getSubgroup() {
        return subgroup;
    }

    @Override
    public List<Subgroup> getSubgroups() {
        return subgroups;
    }

    @Override
    public Subgroup getExternalActiveSubgroup() {
        return externalActiveSubgroup;
    }

    @Override
    public String getTest() {
        return test;
    }

}
