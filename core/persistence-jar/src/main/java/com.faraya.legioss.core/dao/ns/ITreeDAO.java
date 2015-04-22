package com.faraya.legioss.core.dao.ns;

import com.faraya.legioss.core.dao.IGenericDAO;
import com.faraya.legioss.core.entity.ns.Tree;

/**
 * Created by fabrizzio on 4/6/15.
 */
public interface ITreeDAO extends IGenericDAO<Tree,Long> {

    public Tree findByName(String name);

}
