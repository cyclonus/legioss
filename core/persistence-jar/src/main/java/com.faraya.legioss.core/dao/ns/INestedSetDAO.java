package com.faraya.legioss.core.dao.ns;

import com.faraya.legioss.core.dao.IGenericDAO;
import com.faraya.legioss.core.entity.ns.NestedSetNode;

import java.util.List;

/**
 * User: fabrizzio
 * Date: 6/9/13
 * Time: 10:10 PM
 */
public interface INestedSetDAO extends IGenericDAO<NestedSetNode,Long> {

    public NestedSetNode findById(Long id);

    public NestedSetNode findByName(String name);

    public NestedSetNode findRoot();

    public NestedSetNode add(NestedSetNode node);

    public NestedSetNode add(NestedSetNode newNode,NestedSetNode parent);

    public boolean delete(NestedSetNode node);

    public List<NestedSetNode> getTree(Long rootId);

    public NestedSetNode getRightMostNodeFor(NestedSetNode parent);

    public NestedSetNode getLeftMostNodeFor(NestedSetNode parent);

}
