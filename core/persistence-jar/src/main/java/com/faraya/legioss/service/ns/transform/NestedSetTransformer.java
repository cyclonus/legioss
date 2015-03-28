package com.faraya.legioss.service.ns.transform;

import com.faraya.legioss.core.entity.ns.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fabrizzio on 3/17/15.
 */

public class NestedSetTransformer {

    private Long treeId;

    private List<Node> subTree;

    private Map<Long,Node> indexMap;

    private NestedSetTransformer(Long treeId, List<Node> subTree) {
        this.treeId = treeId;
        this.subTree = subTree;
    }

    private Map<Long,Node> getIndexMap(List<Node> subTree){
       if(indexMap == null){
         indexMap = new HashMap<Long, Node>(subTree.size());
         for(Node n: subTree){
            indexMap.put(n.getId(),n);
         }
       }
       return indexMap;
    }


}
