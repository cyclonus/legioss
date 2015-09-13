package com.faraya.legioss.core.dao.common;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.common.Contact;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Created by fabrizzio on 9/12/15.
 */

@Repository
public class ContactDAO extends AbstractJPAGenericDAO <Contact,Long> implements IContactDAO{

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public ContactDAO() {
        super(Contact.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
