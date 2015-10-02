package com.altr.core.dao;


import com.altr.core.model.*;

import java.util.List;

public interface TObjectDAO
{
    public TObject getObjectById(int objectId);
    public TAttribute getAttributeById(int id);
    public TAttrGroup getAttrGroupById(int id);
    public TParam getParamByObjectAndAttr(int object, int attribute);
    public TListValue getListValueById(int id);
    public void saveParam(TParam param);
    public void delete(Object param);
}
