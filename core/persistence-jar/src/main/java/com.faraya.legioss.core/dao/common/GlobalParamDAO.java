package com.faraya.legioss.core.dao.common;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.common.GlobalParam;
import com.faraya.legioss.core.entity.common.GlobalParams;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 * Created by fabrizzio on 10/31/15.
 */

@Repository
public class GlobalParamDAO extends AbstractJPAGenericDAO<GlobalParam, Long> implements IGlobalParamDAO {

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public GlobalParamDAO() {
        super(GlobalParam.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<GlobalParam> findAllByGroup(String group) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GlobalParam> criteriaQuery = criteriaBuilder.createQuery(GlobalParam.class);
        Root<GlobalParam> entity = criteriaQuery.from(GlobalParam.class);
        criteriaQuery.select(entity);
        CriteriaQuery cq = criteriaQuery.where(criteriaBuilder.equal(entity.get("suite"), group));
        Query query = entityManager.createQuery(cq);
        @SuppressWarnings("unchecked")
        List<GlobalParam> result = query.getResultList();
        return result;
    }

    public GlobalParam findByGroupAndName(String group, String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GlobalParam> criteriaQuery = criteriaBuilder.createQuery(GlobalParam.class);
        Root<GlobalParam> entity = criteriaQuery.from(GlobalParam.class);
        criteriaQuery.select(entity);
        CriteriaQuery cq = criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(entity.get("suite"), group),
                        criteriaBuilder.equal(entity.get("name"), name)
                )
        );
        Query query = entityManager.createQuery(cq);
        @SuppressWarnings("unchecked")
        GlobalParam result = GlobalParam.class.cast(query.getSingleResult());
        return result;
    }

    public void setHoursStandardShift(Integer val){
        GlobalParam gp = findByGroupAndName("",GlobalParams.HOURS_STANDARD_SHIFT.name());
        gp.setValue(val + "");
        save(gp);
    }

    public Integer getHoursStandardShift(){
        GlobalParam gp = findByGroupAndName("",GlobalParams.HOURS_STANDARD_SHIFT.name());
        return Integer.valueOf(gp.getValue());
    }
}
