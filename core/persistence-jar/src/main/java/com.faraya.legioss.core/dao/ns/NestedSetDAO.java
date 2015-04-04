package com.faraya.legioss.core.dao.ns;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.ns.NestedSetNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
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

check against : http://www.developer.com/db/article.php/3517366/Using-the-Nested-Set-Data-Model-for-Breadcrumb-Links.htm


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
   see from Figure 1 we have a total of 6 nodes in our ns.
 - A leaf node (a node that doesn't have children) is always a node
   that when you subtract the lft value from the rgt value, the result must be 1.
   Lets look at node B as an example: 3 – 2 = 1
   Now lets look at another leaf node, node E: 8 – 7 = 1

 - If we wanted to know how many child nodes are under a specific parent node we can use the following formula.
   ((parent.rgt – 1) – parent.lft) / 2
   Lets use node C as an example: ((11 – 1) – 4) / 2 = 3 nodes
 *
 */

@Repository
public class NestedSetDAO extends AbstractJPAGenericDAO<NestedSetNode,Long> implements INestedSetDAO {

    Logger logger = LoggerFactory.getLogger(NestedSetDAO.class);

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public NestedSetDAO() {
        super(NestedSetNode.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public NestedSetNode findById(Long id){
      return getEntityManager().find(NestedSetNode.class,id);
    }

    public NestedSetNode findByName(String name){
        NestedSetNode node = null;
        Query query = getEntityManager().createQuery(" SELECT n FROM NestedSetNode n WHERE n.name = :n ", NestedSetNode.class);
        try {
            query.setParameter("n", name);
            node = (NestedSetNode) query.getSingleResult();
        } catch (NoResultException nre) {
            logger.warn(" Node not found under name :"+name);
        }
        return node;
    }

    public NestedSetNode findRoot() {
        NestedSetNode root = null;
        Query query = getEntityManager().createQuery(" SELECT n FROM NestedSetNode n WHERE n.left = 1 ", NestedSetNode.class);
        try {
            root = (NestedSetNode) query.getSingleResult();
        } catch (NoResultException nre) {
            logger.warn(" No root tree was found ");
        }
        return root;
    }

    public NestedSetNode add(NestedSetNode newNode){
         return add(newNode,null);
    }

    private int shiftLeft(int inc, int after){
        String update = " UPDATE NestedSetNode n set n.left = n.left + :inc WHERE n.left > :l ";
        Query updateQuery = getEntityManager().createQuery(update);
        updateQuery.setParameter("inc", inc);
        updateQuery.setParameter("l", after);
        int rows = (updateQuery.executeUpdate());
        logger.info("shiftLeft " + rows);
        return rows;
    }

    private int shiftRight(int inc, int after){
        String update = " UPDATE NestedSetNode n set n.right = n.right + :inc WHERE n.right > :r ";
        Query updateQuery = getEntityManager().createQuery(update);
        updateQuery.setParameter("inc", inc);
        updateQuery.setParameter("r", after);
        int rows = (updateQuery.executeUpdate());
        logger.info("shiftRight " + rows);
        return rows;
    }

    /**
     * Shifts all nodes in the Nested set - Tree
     * New Transaction is needed to force commits
     * @param shiftAfter
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void shiftNodes(int shiftAfter){
        final int inc = 2;
        shiftRight(inc, shiftAfter);
        shiftLeft(inc, shiftAfter);
    }

    /**
     * Takes a fresh node instance and a parent node (previously persisted)
     * If you want to see the changes on any other nodes inside a transaction user refresh method
     * This method inerts the new node under the parent and shifts all nodes in the respective tree.
     * If a no parent is specified (passing null), this method will attempt to create a root node
     * If the root node already exists InvalidStateException will raised
     * @param parent
     * @param newNode
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class)
    public NestedSetNode add(NestedSetNode newNode, NestedSetNode parent) {
        if (parent == null) {
            // insert directly under root
            NestedSetNode root = findRoot();
            if (root == null) {
                // no root was found, we are inserting root now!!
                newNode.setParent(null);
                newNode.setLeft(1);
                newNode.setRight(2);
                logger.info(" Creating NEW a root node ");
            } else {
                throw new IllegalStateException("No parent was specified and Root node already exists");
            }
        } else {
            // We always need to know the parent before adding a node
            int newLeft = parent.getLeft() + 1;
            NestedSetNode rm = getRightMostNodeFor(parent);
            if (rm != null) {
                // This should only happen when adding nodes under a parent that already has children
                // So we need to calculate the right most one to add next to it
                newLeft = rm.getRight() + 1;
            }
            int newRight = newLeft + 1;
            logger.info(String.format("New left is %d & New right is %d ",newLeft, newRight));
            newNode.setParent(parent.getId());
            newNode.setLeft(newLeft);
            newNode.setRight(newRight);

            int shiftAfter = newLeft - 1;
            logger.info(" shifting nodes after: "+shiftAfter );
            shiftNodes(shiftAfter);
        }
        save(newNode);
        // update depth if required
        // forces a commit, do not remove!
        flush();
        return newNode;
    }

    public List<NestedSetNode> getTree(Long rootId){
        String ql =
                " SELECT node FROM NestedSetNode node, NestedSetNode parent " +
                     " WHERE node.left > parent.left AND  node.left < parent.right " +
                     " AND parent.id = :id ORDER BY node.left";

        Query query = getEntityManager().createQuery(ql,NestedSetNode.class);
        query.setParameter("id",rootId);
        return query.getResultList();
    }


    public List<NestedSetNode> getLeafNodes(){
       /*
        *  SELECT * FROM NestedTest WHERE lft = (rgt - 1);
        *  (rgt – lft) = 1
        */
        String ql =
                " SELECT node FROM NestedSetNode node " +
                " WHERE  ( node.right = node.left ) = 1 ";
        Query query = getEntityManager().createQuery(ql,NestedSetNode.class);
        return query.getResultList();
    }

    /**
     * Go get me the right most node, below this Parent node
     * @param parent
     * @return
     */
    public NestedSetNode getRightMostNodeFor(NestedSetNode parent){
        logger.debug(" getting the right-most node under: "+parent);
        NestedSetNode rm = null;
        String q1 = " SELECT max(n.right) FROM NestedSetNode n WHERE n.left > :lft AND n.right < :rgt ";
        Query query1 = getEntityManager().createQuery(q1,Number.class);

        try{
            query1.setParameter("lft",parent.getLeft());
            query1.setParameter("rgt",parent.getRight());
            Number max = (Number)query1.getSingleResult();
            if(max != null){
                String q2 = " SELECT n FROM NestedSetNode n WHERE n.right = :max ";
                Query query2 = getEntityManager().createQuery(q2,NestedSetNode.class);
                query2.setParameter("max",max);
                rm  = (NestedSetNode)query2.getSingleResult();
            }
        }catch (NoResultException nre) {
            logger.warn(" No Left Most Node was found for parent "+parent);
        }

        return rm;
    }

    public NestedSetNode getLeftMostNodeFor(NestedSetNode parent){
        NestedSetNode lm = null;
        String q1 = " SELECT min(n.right) FROM NestedSetNode n WHERE n.left > :lft AND n.right < :rgt ";
        Query query1 = getEntityManager().createQuery(q1,Number.class);
        try{
            query1.setParameter("lft",parent.getLeft());
            query1.setParameter("rgt",parent.getRight());
            Number min = (Number)query1.getSingleResult();
            if(min != null){
                String q2 = " SELECT n FROM NestedSetNode n WHERE n.left = :min ";
                Query query2 = getEntityManager().createQuery(q2,NestedSetNode.class);
                query2.setParameter("min",min);
                lm  = (NestedSetNode)query2.getSingleResult();
            }
        }catch (NoResultException nre) {
            logger.warn(" No Left Most Node was found for parent "+parent);
        }

        return lm;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(NestedSetNode node){
        int width = node.getRight() - node.getLeft() + 1;
        int l = node.getLeft();
        int r = node.getRight();
        String delete = "DELETE FROM NestedSetNode n WHERE n.left BETWEEN :l AND :r";
        Query deleteQuery = getEntityManager().createQuery(delete);
        deleteQuery.setParameter("l",l);
        deleteQuery.setParameter("r",r);
        int rows = deleteQuery.executeUpdate();
        flush();
        int inc = -1 * width;

        int sr = shiftRight(inc, r);
        int sl = shiftLeft(inc, l);

        logger.debug(" shift right " + sr);
        logger.debug(" shift left " + sl);
        return (rows > 0);
    }

}
