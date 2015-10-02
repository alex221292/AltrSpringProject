package com.altr.core.service;


import com.altr.core.dao.TObjectDAO;
import com.altr.core.helper.InternalModel.Group;
import com.altr.core.helper.SystemConstants;
import com.altr.core.model.*;
import com.altr.core.sql.SQLStatement;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.util.MultiValueMap;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TObjectServiceImpl implements TObjectService {

    private static final Logger logger = LoggerFactory.getLogger(TObjectServiceImpl.class);
    private TObjectDAO tObjectDAO;

    public void settObjectDAO(TObjectDAO tObjectDAO) {
        this.tObjectDAO = tObjectDAO;
    }

    private SQLStatement sqlStatement;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired(required = true)
    @Qualifier(value = "sqlStatement")
    public void setSqlStatement(SQLStatement st) {
        this.sqlStatement = st;
    }


    @Override
    @Transactional
    public TObject getObjectById(int objectId) {
        return this.tObjectDAO.getObjectById(objectId);
    }

    @Override
    @Transactional
    public TAttribute getAttributeById(int attrId) {
        return this.tObjectDAO.getAttributeById(attrId);
    }

    @Override
    @Transactional
    public TAttrGroup getAttGroupById(int groupId) {
        return this.tObjectDAO.getAttrGroupById(groupId);
    }

    @Override
    @Transactional
    public TListValue getListValueById(int lvId) {
        return this.tObjectDAO.getListValueById(lvId);
    }


    @Override
    @Transactional
    public List<TObject> getChildrenObjectsByParentId(int id) {
        List<TObject> objects = new ArrayList<TObject>();
        try {
            Collection<Integer> objectIds = this.jdbcTemplate.query(SystemConstants.SQL.GET_CHILDREN_ID_BY_PARENT, new Object[]{id}, new SQLStatement.GeneralMapper<Integer>(new Integer(id)));
            for (Integer objectId : objectIds) {
                objects.add(getObjectById(objectId));
            }
        } catch (Exception e) {
            logger.error("[getChildrenObjectsByParentId] Error");
        }
        return objects;
    }

    @Override
    @Transactional
    public List<Group> getGroups(int id) {
        return this.jdbcTemplate.query(SystemConstants.SQL.GET_GROUPS_BY_OBJECT_ID, new Object[]{id}, new SQLStatement.GeneralMapper<Group>(new Group()));
    }

    @Override
    @Transactional
    public TParam getParamByObjectAndAttr(int object, int attribute) throws RuntimeException {
        try {
            return this.tObjectDAO.getParamByObjectAndAttr(object, attribute);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void updateParam(final int objectId, final int attrId, final String value) {
        try {
            if (sqlStatement.getIntBySQL("select 1 from t_params where attr_id = ? and object_id = ?", new Object[]{attrId, objectId}) == 1) {
                TParam param = getParamByObjectAndAttr(objectId, attrId);
                if ("".equals(value)) {
                    jdbcTemplate.execute("DELETE from t_params where attr_id = ? and object_id = ?", new PreparedStatementCallback<Boolean>() {
                        @Override
                        public Boolean doInPreparedStatement(PreparedStatement ps)
                                throws SQLException, DataAccessException {
                            ps.setInt(1, attrId);
                            ps.setInt(2, objectId);
                            return ps.execute();

                        }
                    });
                } else {
                    param.setValue(value);
                }
            } else {
                jdbcTemplate.execute("INSERT into t_params (attr_id, object_id, value) VALUES (?, ?, ?)", new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement ps)
                            throws SQLException, DataAccessException {
                        ps.setInt(1, attrId);
                        ps.setInt(2, objectId);
                        ps.setString(3, value);
                        return ps.execute();

                    }
                });
            }
        /*if (param == null) {
            logger.info("[updateParam] insert");
            TParam newParam = new TParam();
            newParam.setObjectId(objectId);
            newParam.setAttrId(attrId);
            newParam.setValue(value);
            tObjectDAO.saveParam(newParam);

        } else {

        }*/
        } catch (Exception e) {
            logger.info("update" + e.toString());
        }
    }

    @Override
    @Transactional
    public void updateParamBulk(int objectId, Map<String, String> updateParam) {
        for (Map.Entry<String, String> entry : updateParam.entrySet()) {
            updateParam(objectId, Integer.parseInt(entry.getKey()), entry.getValue());
        }
    }

}
