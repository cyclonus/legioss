package com.faraya.legioss.service.ns.transform;

/**
 * Created by fabrizzio on 3/17/15.
 * Entry point for a hierarchical structure returned from a NestedSetNodesTransformer
 */
public interface ITree<T> {

    /**
     * unique tree identifier
     * @return
     */
    Long getId();

    /**
     * Root node
     * @return
     */
    INode<T> getRoot();

}
