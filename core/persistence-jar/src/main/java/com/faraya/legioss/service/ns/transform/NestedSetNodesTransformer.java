package com.faraya.legioss.service.ns.transform;

import com.faraya.legioss.core.entity.ns.NestedSetNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by fabrizzio on 3/17/15.
 * This class is meant to take a flat structure made out of a nested set query
 * and transform it into a tree (Parent-children like) structure
 */

public class NestedSetNodesTransformer<ID, N extends NestedSetNode> {

    Logger logger = LoggerFactory.getLogger(NestedSetNodesTransformer.class);

    private List<N> subTree;

    private INodeInstanceFactory instanceFactory;

    private INode<ID> root;

    private ITree<ID> tree;

    /**
     * Since Nodes can have many different implementations an node instance factory is required
     * @param subTree
     * The factory that will convert from NestedSetNode to a concert Node Representation
     * @param instanceFactory
     */
    public NestedSetNodesTransformer(List<N> subTree, INodeInstanceFactory<ID, N> instanceFactory) {
        this.subTree = subTree;
        this.instanceFactory = instanceFactory;
    }

    private ITree <ID> transform()  {
        //TODO: Refactor this into a separate strategy and make it the default one
        final Map<ID, INode<ID>> mappedNodes = new HashMap<ID, INode<ID>>();
        Collections.sort(subTree, new LeftComparator());
        for (int i = 0; i <= subTree.size() - 1; i++) {
            N ns = subTree.get(i);
            @SuppressWarnings("unchecked")
            INode<ID> node = instanceFactory.newInstance(ns);
            mappedNodes.put(node.getId(), node);
            if (node.getParentId() != null) {
                INode<ID> parent = mappedNodes.get(node.getParentId());
                if (parent != null) {
                    parent.addChild(node);
                }
            } else {
                if (node.isRoot()) {
                    // Throw IllegalStateException here if we find more than one root node
                    if(root != null)
                       throw new IllegalStateException(" Root node was already set, another node claims to be root, ");
                     root = node;
                } else {
                    logger.error(" Data integrity err ");
                }
            }
        }

        return new ITree<ID>() {

            public ID getId() {
                return root.getId();
            }

            public INode <ID> getRoot() {
                return root;
            }
        };
    }

    public ITree <ID> transformAndGet() {
        if(tree == null){
           tree = transform();
        }
        return tree;
    }

    /**
     * LeftComparator used to sort the flat nested set structure using the values stored in NestedSetNode.left
     */
    private static class LeftComparator implements Comparator<NestedSetNode> {

        public int compare(NestedSetNode o1, NestedSetNode o2) {
            return o1.getLeft().intValue() - o2.getLeft().intValue();
        }

    }

}
