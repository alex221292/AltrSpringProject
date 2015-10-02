package com.altr.core.dao;

import com.altr.core.model.TAttribute;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TAttributeDAOImpl implements TAttributeDAO {

    private static final Logger logger = LoggerFactory.getLogger(TAttributeDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf)
    {
        this.sessionFactory = sf;
    }

    @Override
    public TAttribute getAttributeById(int id)
    {
        Session session = this.sessionFactory.getCurrentSession();
        TAttribute tAttribute = (TAttribute) session.get(TAttribute.class, new Integer(id));
        return tAttribute;
    }

}
