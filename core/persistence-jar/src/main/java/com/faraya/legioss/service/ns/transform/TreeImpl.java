package com.faraya.legioss.service.ns.transform;

/**
 * Created by fabrizzio on 3/17/15.
 * Entry point for a hierarchical structure returned from a NestedSetNodesTransformer
 * Default implementation
 */
public class TreeImpl implements ITree <Long> {

    private Long id;


    private INode<Long> root;

    public TreeImpl() {
    }

    public TreeImpl(Long id, INode<Long> root) {
        this.id = id;
        this.root = root;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public INode<Long> getRoot() {
        return root;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setRoot(INode<Long> root) {
        this.root = root;
    }
}
