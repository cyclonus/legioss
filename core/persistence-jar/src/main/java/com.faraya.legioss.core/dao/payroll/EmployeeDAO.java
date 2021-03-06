package com.faraya.legioss.core.dao.payroll;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.payroll.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Created by fabrizzio on 10/4/15.
 */

@Repository
public class EmployeeDAO extends AbstractJPAGenericDAO<Employee,Long> implements IEmployeeDAO {

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public EmployeeDAO() {
        super(Employee.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }


}
