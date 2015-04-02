package com.faraya.legioss.service.ns.transform;

import java.util.List;

/**
 * Created by fabrizzio on 3/17/15.
 */
public interface INode<T>{

    T getId();

    T getParentId();

    List<INode<T>> getChildren();

    void addChild(INode<T> node);

    boolean isRoot();

    boolean isLeaf();

}
