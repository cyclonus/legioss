package com.faraya.legioss.service.ns.transform;


import com.faraya.legioss.core.entity.accounting.AccountNode;

/**
 * Created by fabrizzio on 3/31/15.
 * Default Nodes Factory implementation
 * This will take whatever nested-set node type is passed to it and will return
 * an instance of ChildImpl which is the basic type to represent a nested-set node
 */
public class DefaultNodeInstanceFactory implements INodeInstanceFactory <Long,AccountNode> {

    @Override
    public INode<Long> newInstance(AccountNode node) {
        return new ChildImpl(node.getId(),node.getParent(), node.getName(),
                node.getAccount() == null ? null :
                node.getAccount().getAccountType());
    }

}
