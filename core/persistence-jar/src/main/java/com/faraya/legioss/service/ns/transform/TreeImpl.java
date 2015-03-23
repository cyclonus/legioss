package com.faraya.legioss.service.ns.transform;

/**
 * Created by fabrizzio on 3/17/15.
 */
public class TreeImpl implements ITree <Long> {

    private Long id;

    private IChild<Long> root;

    public TreeImpl(Long id, IChild<Long> root) {
        this.id = id;
        this.root = root;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public IChild <Long> getRoot() {
        return root;
    }
}
