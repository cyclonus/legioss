package net.faraya.legioss.core.dao;

import net.faraya.legioss.core.IIdentifiable;

import java.io.Serializable;
import java.util.List;

/**
 * User: fabrizzio
 * Date: 6/7/13
 * Time: 10:30 PM
 */
public interface IGenericDAO <T extends IIdentifiable, PK extends Serializable>{

    List<T> find(int from, int max);

    T findByPK(PK id);

    T save(T o);

    T merge(T o);

    void remove(T o);

    void refresh(T o);
}
