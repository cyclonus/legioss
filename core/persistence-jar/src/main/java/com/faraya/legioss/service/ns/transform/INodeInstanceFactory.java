package com.faraya.legioss.service.ns.transform;

import com.faraya.legioss.core.entity.ns.NestedSetNode;

/**
 * Created by fabrizzio on 3/29/15.
 */

public interface INodeInstanceFactory <ID, S extends NestedSetNode> {

    /**
     * @param node
     * @return
     */
    INode <ID> newInstance( S node );

}
