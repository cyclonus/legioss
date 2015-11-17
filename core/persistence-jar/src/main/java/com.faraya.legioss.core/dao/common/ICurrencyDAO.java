package com.faraya.legioss.core.dao.common;

import com.faraya.legioss.core.dao.IGenericDAO;
import com.faraya.legioss.core.entity.common.Currency;

/**
 *
 * Created by fabrizzio on 5/10/15.
 */

public interface ICurrencyDAO extends IGenericDAO<Currency,Long> {

    Currency findByShortName(String name);

}
