package com.faraya.legioss.service.ns.transform;

import java.util.List;

/**
 * Created by fabrizzio on 3/17/15.
 */
public interface IChild <T>{

    T getId();

    T getParentId();

    List<IChild> getChildren();

}
