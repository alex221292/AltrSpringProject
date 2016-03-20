package com.altr.core.dao;

import com.altr.core.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TObjectDAOImpl implements TObjectDAO {
    private static final Logger logger = LoggerFactory.getLogger(TObjectDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
     public TObject getObjectById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        TObject tObject = (TObject) session.get(TObject.class, new Integer(id));
        return tObject;
    }

    @Override
    public TObjectType getObjectTypeById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        TObjectType tObjectType = (TObjectType) session.get(TObjectType.class, new Integer(id));
        return tObjectType;
    }

    @Override
    public TAttribute getAttributeById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        TAttribute tAttribute = (TAttribute) session.get(TAttribute.class, new Integer(id));
        return tAttribute;
    }

    @Override
    public TAttrGroup getAttrGroupById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        TAttrGroup tAttrGroup = (TAttrGroup) session.get(TAttrGroup.class, new Integer(id));
        return tAttrGroup;
    }

    @Override
    public TParam getParamByObjectAndAttr(int object, int attribute) throws RuntimeException {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            Query query = session.createQuery("from TParam tParam where tParam.id.attrId = ? and tParam.id.objectId = ?");
            query.setInteger(0, attribute);
            query.setInteger(1, object);
            TParam tParam = (TParam) query.list().get(0);
            return tParam;
        } catch (Exception e) {
            throw new RuntimeException("Param is empty");
        }
    }

    @Override
    public TListValue getListValueById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        TListValue tListValue = (TListValue) session.get(TListValue.class, new Integer(id));
        return tListValue;
    }

    @Override
    public void save(Object object) {
        try {
            Session session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            logger.info("[saveParam] " + e.getMessage());
        }
    }

    @Override
    public void delete(Object param) {
        try {
            Session session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.delete(param);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            logger.error("[saveParam] Error");
        }
    }

}
