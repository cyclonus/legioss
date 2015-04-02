package com.faraya.legioss.service.ns.transform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabrizzio on 3/31/15.
 */
public class ChildImpl implements INode <Long> {

    private Long id;

    private Long parentId;

    private String label;

    public ChildImpl(Long id, Long parentId, String label) {
        this.id = id;
        this.parentId = parentId;
        this.label = label;
    }

    private List<INode<Long>> children;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getParentId() {
        return parentId;
    }

    @Override
    public List<INode<Long>> getChildren() {
        if(children == null){
           children = new ArrayList<INode<Long>>();
        }
        return children;
    }

    @Override
    public void addChild(INode<Long> node) {
         getChildren().add(node);
    }

    @Override
    public boolean isRoot() {
        return getParentId() == null;
    }

    @Override
    public boolean isLeaf() {
        return getChildren().isEmpty();
    }
}
