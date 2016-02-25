package com.faraya.legioss.core.dao.costing;

import com.faraya.legioss.core.dao.IGenericDAO;
import com.faraya.legioss.core.entity.costing.Piecework;

/**
 *
 * Created by fabrizzio on 10/11/15.
 */

public interface IPieceworkDAO extends IGenericDAO<Piecework,Long> {

    Piecework findByCode(String code);

    Piecework findByName(String name);

}
