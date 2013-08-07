package net.faraya.legioss.core.dao.accounting;

import net.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import net.faraya.legioss.core.entity.tree.Node;
import org.hibernate.CacheMode;
import org.hibernate.ejb.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * User: fabrizzio
 * Date: 6/9/13
 * Time: 10:03 PM
 *



 NestedTest
 node lft rgt
 'A'   1  12
 'B'   2  3
 'C'   4  11
 'D'   5  6
 'E'   7  8
 'F'   9  10


 The Root node will always contain a 1 in the lft field.

 - The amount of nodes in our hierarchy is the rgt value of our
   Root node divided by 2. In this case 12 / 2 = 6. And as you can
   see from Figure 1 we have a total of 6 nodes in our tree.
 - A leaf node (a node that doesn't have children) is always a node
   that when you subtract the lft value from the rgt value, the result must be 1.
   Lets look at node B as an example: 3 – 2 = 1
   Now lets look at another leaf node, node E: 8 – 7 = 1

 - If we wanted to know how many child nodes are under a specif-
   ic parent node we can use the following formula.
   ((parent.rgt – 1) – parent.lft) / 2
   Lets use node C as an example: ((11 – 1) – 4) / 2 = 3 nodes
 *
 */

@Repository
public class NodeDAO extends AbstractJPAGenericDAO<Node,Long> implements ICatalogDAO {

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public NodeDAO() {
        super(Node.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Node findById(Long id){
      return getEntityManager().find(Node.class,id);
    }

    public Node findByName(String name){
        Node node = null;
        Query query = getEntityManager().createQuery(" SELECT n FROM Node n WHERE n.name = :n ", Node.class);
        try {
            query.setHint(QueryHints.HINT_CACHE_MODE, CacheMode.IGNORE);
            query.setParameter("n", name);
            node = (Node) query.getSingleResult();
        } catch (NoResultException nre) {
        }
        return node;
    }

    public Node findRoot() {
        Node root = null;
        Query query = getEntityManager().createQuery(" SELECT n FROM Node n WHERE n.left = 1 ", Node.class);
        try {
            query.setHint(QueryHints.HINT_CACHE_MODE, CacheMode.IGNORE);
            root = (Node) query.getSingleResult();
        } catch (NoResultException nre) {
        }
        return root;
    }

    public Node add(Node newNode){
         return add(null,newNode);
    }

    private int shiftLeft(int inc,int after){
        String update = " UPDATE Node n set n.left = n.left + :inc WHERE n.left > :l ";
        Query updateQuery = getEntityManager().createQuery(update);
        updateQuery.setParameter("inc", inc);
        updateQuery.setParameter("l", after);
        int rows = (updateQuery.executeUpdate());
        System.out.println("shiftLeft " + rows);
        return rows;
    }

    private int shiftRight(int inc,int after){
        String update = " UPDATE Node n set n.right = n.right + :inc WHERE n.right > :r ";
        Query updateQuery = getEntityManager().createQuery(update);
        updateQuery.setParameter("inc", inc);
        updateQuery.setParameter("r", after);
        int rows = (updateQuery.executeUpdate());
        System.out.println("shiftRight " + rows);
        return rows;
    }

    /**
     *
     * @param parent
     * @param newNode
     * @return
     */
    @Transactional()
    public Node add(Node parent, Node newNode) {
        if (parent == null) {
            // insert directly under root
            Node root = findRoot();
            if (root == null) {
                // no root was found, we are inserting root
                newNode.setParent(null);
                newNode.setLeft(1);
                newNode.setRight(2);
            }
        } else {

            int newLeft = parent.getLeft() + 1;
            Node rm = getRightMostNode(parent);
            if (rm != null) {
                newLeft = rm.getRight() + 1;
            }
            newNode.setParent(parent.getId());
            newNode.setLeft(newLeft);
            newNode.setRight(newLeft + 1);

            int after = newLeft - 1;
            shiftRight(2, after);
            shiftLeft(2, after);
        }
        save(newNode);
        // update depth if required
        if(parent != null && parent.getId() != null){
           getEntityManager().refresh(parent);
        }
        // clear();
        return newNode;
    }

    public List<Node> getTree(Long rootId){
        String ql =
                " SELECT node FROM Node node, Node parent " +
                     " WHERE node.left > parent.left AND  node.left < parent.right " +
                     " AND parent.id = :id ORDER BY node.left";

        Query query = getEntityManager().createQuery(ql,Node.class);
        query.setHint(QueryHints.HINT_CACHE_MODE, CacheMode.IGNORE);
        query.setParameter("id",rootId);
        return query.getResultList();
    }


    public List<Node> getLeafNodes(){
       /*
        *  SELECT * FROM NestedTest WHERE lft = (rgt - 1);
        *  (rgt – lft) = 1
        */
        String ql =
                " SELECT node FROM Node node " +
                " WHERE  ( node.right = node.left ) = 1 ";
        Query query = getEntityManager().createQuery(ql,Node.class);
        return query.getResultList();
    }

    public Node getRightMostNode(Node parent){
        Node rm = null;
        String q1 = " SELECT max(n.right) FROM Node n WHERE n.left > :lft AND n.right < :rgt ";
        Query query1 = getEntityManager().createQuery(q1,Number.class);
        Number max;
        String q2 = " SELECT n FROM Node n WHERE n.right = :max ";
        Query query2 = getEntityManager().createQuery(q2,Node.class);
        try{
            query1.setHint(QueryHints.HINT_CACHE_MODE, CacheMode.IGNORE);
            query1.setParameter("lft",parent.getLeft());
            query1.setParameter("rgt",parent.getRight());
            max = (Number)query1.getSingleResult();
            if(max != null){
               query2.setHint(QueryHints.HINT_CACHE_MODE, CacheMode.IGNORE);
               query2.setParameter("max",max);
               rm  = (Node)query2.getSingleResult();
            }
        }catch (NoResultException nre){

        }

        return rm;
    }

    public Node getLeftMostNode(Node parent){
        Node lm = null;
        String q1 = " SELECT min(n.right) FROM Node n WHERE n.left > :lft AND n.right < :rgt ";
        Query query1 = getEntityManager().createQuery(q1,Number.class);
        Number min;
        String q2 = " SELECT n FROM Node n WHERE n.left = :min ";
        Query query2 = getEntityManager().createQuery(q2,Node.class);
        try{
            query1.setHint(QueryHints.HINT_CACHE_MODE, CacheMode.IGNORE);
            query1.setParameter("lft",parent.getLeft());
            query1.setParameter("rgt",parent.getRight());
            min = (Number)query1.getSingleResult();
            if(min != null){
                query2.setHint(QueryHints.HINT_CACHE_MODE, CacheMode.IGNORE);
                query2.setParameter("min",min);
                lm  = (Node)query2.getSingleResult();
            }
        }catch (NoResultException nre){

        }

        return lm;
    }

    @Transactional
    public boolean delete(Node node){
        int width = node.getRight() - node.getLeft() + 1;
        int l = node.getLeft();
        int r = node.getRight();
        String delete = "DELETE FROM Node n WHERE n.left BETWEEN :l AND :r";
        Query deleteQuery = getEntityManager().createQuery(delete);
        deleteQuery.setParameter("l",l);
        deleteQuery.setParameter("r",r);
        int rows = deleteQuery.executeUpdate();
        flush();
        int inc = -1 * width;
        System.out.println(shiftRight(inc,r));
        System.out.println(shiftLeft(inc,l));
        return (rows > 0);
    }

}
