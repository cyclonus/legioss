package com.faraya.legioss.service.ns.transform;

import com.faraya.legioss.core.model.accounting.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabrizzio on 3/31/15.
 */
public class ChildImpl implements INode <Long> {

    private Long id;

    private Long parentId;

    private String name;

    private AccountType type;

    public ChildImpl() {
    }

    public ChildImpl(Long id, Long parentId, String name, AccountType type) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.type = type;
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
           children = new ArrayList<>();
        }
        return children;
    }

    @Override
    public void addChild(INode<Long> node) {
         getChildren().add(node);
    }


    @Override
    @JsonIgnore
    public boolean isRoot() {
        return getParentId() == null;
    }

    @Override
    @JsonIgnore
    public boolean isLeaf() {
        return getChildren().isEmpty();
    }

    public String getName() {
        return name;
    }

    public AccountType getType() {
        return type;
    }
}
