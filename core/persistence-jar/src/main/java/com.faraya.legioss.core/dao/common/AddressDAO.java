package com.faraya.legioss.core.dao.common;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.common.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Created by fabrizzio on 9/30/15.
 */

@Repository
public class AddressDAO extends AbstractJPAGenericDAO<Address,Long> implements IAddressDAO{

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public AddressDAO() {
        super(Address.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
