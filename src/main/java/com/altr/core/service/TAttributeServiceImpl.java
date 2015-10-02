package com.altr.core.service;

import com.altr.core.dao.TAttributeDAO;
import com.altr.core.model.TAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;

public class TAttributeServiceImpl implements TAttributeService {
    private static final Logger logger = LoggerFactory.getLogger(TAttributeService.class);
    private TAttributeDAO tAttributeDAO;

    public void settAttributeDAO(TAttributeDAO tAttributeDAO) {
        this.tAttributeDAO = tAttributeDAO;
    }

    @Override
    @Transactional
    public TAttribute getAttributeById(int attrId) {
        return this.tAttributeDAO.getAttributeById(attrId);
    }
}
