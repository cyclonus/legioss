package net.faraya.legioss.core.dao.accounting;

import net.faraya.legioss.core.dao.IGenericDAO;
import net.faraya.legioss.core.entity.tree.Node;

import java.util.List;

/**
 * User: fabrizzio
 * Date: 6/9/13
 * Time: 10:10 PM
 */
public interface ICatalogDAO extends IGenericDAO<Node,Long> {

    public Node findById(Long id);

    public Node findByName(String name);

    public Node findRoot();

    public Node add(Node node);

    public Node add(Node parent, Node newNode);

    public boolean delete(Node node);

    public List<Node> getTree(Long rootId);

    public Node getRightMostNode(Node parent);

    public Node getLeftMostNode(Node parent);

}
