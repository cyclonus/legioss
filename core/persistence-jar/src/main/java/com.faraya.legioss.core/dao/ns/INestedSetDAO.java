package com.faraya.legioss.core.dao.ns;

import com.faraya.legioss.core.dao.IGenericDAO;
import com.faraya.legioss.core.entity.ns.Node;

import java.util.List;

/**
 * User: fabrizzio
 * Date: 6/9/13
 * Time: 10:10 PM
 */
public interface INestedSetDAO extends IGenericDAO<Node,Long> {

    public Node findById(Long id);

    public Node findByName(String name);

    public Node findRoot();

    public Node add(Node node);

    public Node add(Node newNode,Node parent);

    public boolean delete(Node node);

    public List<Node> getTree(Long rootId);

    public Node getRightMostNode(Node parent);

    public Node getLeftMostNode(Node parent);

}
