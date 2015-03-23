package com.faraya.legioss.service.ns.transform;

import com.faraya.legioss.core.entity.ns.Node;

import java.util.List;

/**
 * Created by fabrizzio on 3/17/15.
 */

public class NestedSetTreeTransformer {

    public static ITree nestedSetsTransform(Long treeId, List<Node> subTree){

        //Map<Long,Node> index =  buildIndex(subTree);
        //Node root = getRootNode(index);

         ITree<Long> tree = new TreeImpl(treeId, null);

         return tree;
    }

}
