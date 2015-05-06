package com.faraya.legioss.core.dao.ns;

import com.faraya.legioss.core.dao.IGenericDAO;
import com.faraya.legioss.core.entity.ns.NestedSetTree;

/**
 * Created by fabrizzio on 4/6/15.
 */
public interface ITreeDAO <T extends NestedSetTree>extends IGenericDAO<T,Long> {

     T findByName(String name);

}
