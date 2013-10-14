package com.faraya.legioss.core.dao;

import com.faraya.legioss.core.IIdentifiable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * User: fabrizzio
 * Date: 6/5/13
 * Time: 11:14 PM
 */
public abstract class AbstractJPAGenericDAO<T extends IIdentifiable, PK extends Serializable> implements IGenericDAO <T,PK> {

    protected final Log log = LogFactory.getLog(getClass());

    protected Class<T> persistentClass;

    public AbstractJPAGenericDAO(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Transactional
    public T save(T o) {
        this.getEntityManager().persist(o);
        return o;
    }

    @Transactional
    public void remove(T o) {
        if (this.getEntityManager().contains(o)) {
            this.getEntityManager().remove(o);
        } else {
            Object attached = getEntityManager().find(o.getClass(), o.getId());
            this.getEntityManager().remove(attached);
        }
    }

    @Transactional
    public void refresh(T o){
        if(o.getId() != null ){
           getEntityManager().refresh(o);
        }
    }

    @Transactional
    public void flush() {
        this.getEntityManager().flush();
    }

    @Transactional
    public void clear(){
        getEntityManager().clear();
    }

    @Transactional
    public T merge(T o) {
        this.getEntityManager().joinTransaction();
        T merged = this.getEntityManager().merge(o);
        return merged;
    }

    @Override
    public List<T> find(int from, int max) {
        String statement = "SELECT o FROM "+getPersistentClass().getSimpleName() + " o ";
        Query q = getEntityManager().createQuery(statement);
        q.setFirstResult(from);
        q.setMaxResults(max);
        return q.getResultList();
    }

    @Override
    public T findByPK(PK id) {
        return getEntityManager().find(getPersistentClass(),id);
    }

    public abstract EntityManager getEntityManager();
}
