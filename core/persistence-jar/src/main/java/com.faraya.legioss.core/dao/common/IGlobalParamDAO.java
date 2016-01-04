package com.faraya.legioss.core.dao.common;

import com.faraya.legioss.core.dao.IGenericDAO;
import com.faraya.legioss.core.entity.common.GlobalParam;

import java.util.List;

/**
 *
 * Created by fabrizzio on 10/31/15.
 */

public interface IGlobalParamDAO extends IGenericDAO<GlobalParam,Long> {

    List<GlobalParam> findAllByGroup(String group);

    GlobalParam findByGroupAndName(String group, String name);

    Integer getHoursStandardShift();

}
