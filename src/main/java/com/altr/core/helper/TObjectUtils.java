package com.altr.core.helper;


import com.altr.core.sql.SQLStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class TObjectUtils {
    private static final Logger logger = LoggerFactory.getLogger(TObjectUtils.class);

    private SQLStatement sqlStatement;

    @Autowired(required = true)
    @Qualifier(value = "sqlStatement")
    public void setSqlStatement(SQLStatement st) {
        this.sqlStatement = st;
    }

    public String test() {
        return "test";
    }

    @Transactional
    public String getTextParameter(Integer objectId, Integer attrId) {
        return sqlStatement.getStringBySQL(SystemConstants.SQL.GET_PARAMETER_VALUE_BY_OBJECT_AND_ATTR, new Object[]{objectId, attrId});
    }

    public void init() {

    }

    public void destroy() {

    }


}
