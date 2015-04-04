package com.faraya.legioss.core.dao;

import com.faraya.legioss.core.IIdentifiable;

import java.io.Serializable;
import java.util.List;

/**
 * User: fabrizzio
 * Date: 6/7/13
 * Time: 10:30 PM
 */
public interface IGenericDAO <T extends IIdentifiable, PK extends Serializable>{

    List<T> find(int from, int max);

    List<T> listAll();

    T findByPK(PK id);

    T save(T o);

    T merge(T o);

    void remove(T o);

    void refresh(T o);

    void invalidateCache();
}
