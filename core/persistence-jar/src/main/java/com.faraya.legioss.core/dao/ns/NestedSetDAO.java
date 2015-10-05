package com.faraya.legioss.core.dao.ns;

import com.faraya.legioss.core.dao.AbstractJPAGenericDAO;
import com.faraya.legioss.core.entity.ns.NestedSetNode;

import com.faraya.legioss.core.entity.ns.NestedSetTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

public abstract class NestedSetDAO <N extends NestedSetNode, T extends NestedSetTree> extends AbstractJPAGenericDAO<N,Long> implements INestedSetDAO<N,T> {

    Logger logger = LoggerFactory.getLogger(NestedSetDAO.class);

    @PersistenceContext(unitName = "legioss")
    EntityManager entityManager;

    public NestedSetDAO(final Class<N> persistentClass) {
        super(persistentClass);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public N findById(Long id){
      return getEntityManager().find(getPersistentClass(),id);
    }

    public N findByName(String name, T tree){
        String clazzName = getPersistentClass().getSimpleName();
        String queryStr = String.format(" SELECT n FROM %s n WHERE n.name = :n AND n.tree.id = :treeId ", clazzName);
        N node = null;
        Query query = getEntityManager().createQuery(queryStr, getPersistentClass());
        try {
            query.setParameter("n", name);
            query.setParameter("treeId", tree.getId());
            node = getPersistentClass().cast(query.getSingleResult());
        } catch (NoResultException nre) {
            logger.warn(" Node not found under name :"+name);
        }
        return node;
    }

    public N findRoot(T tree) {
        N root = null;
        String clazzName = getPersistentClass().getSimpleName();
        String queryStr = String.format(" SELECT n FROM %s n WHERE n.left = 1 AND n.tree.id = :treeId ", clazzName);
        Query query = getEntityManager().createQuery(queryStr, getPersistentClass());
        try {
            query.setParameter("treeId", tree.getId());
            root = getPersistentClass().cast(query.getSingleResult());
        } catch (NoResultException nre) {
            logger.warn(" No root tree was found ");
        }
        return root;
    }

    private int shiftLeft(Long inc, Long after, Long treeId){
        String clazzName = getPersistentClass().getSimpleName();
        String update = String.format(" UPDATE %s n set n.left = n.left + :inc WHERE n.left > :l AND n.tree.id = :treeId ",clazzName);
        Query updateQuery = getEntityManager().createQuery(update);
        updateQuery.setParameter("inc", inc);
        updateQuery.setParameter("l", after);
        updateQuery.setParameter("treeId", treeId);
        int rows = (updateQuery.executeUpdate());
        logger.info("shiftLeft " + rows);
        return rows;
    }

    private int shiftRight(Long inc, Long after, Long treeId){
        String clazzName = getPersistentClass().getSimpleName();
        String update = String.format(" UPDATE %s n set n.right = n.right + :inc WHERE n.right > :r AND n.tree.id = :treeId ",clazzName);
        Query updateQuery = getEntityManager().createQuery(update);
        updateQuery.setParameter("inc", inc);
        updateQuery.setParameter("r", after);
        updateQuery.setParameter("treeId", treeId);
        int rows = (updateQuery.executeUpdate());
        logger.info("shiftRight " + rows);
        return rows;
    }

    /**
     * Shifts all nodes in the Nested set - NestedSetTree
     * New Transaction is needed to force commits
     * @param shiftAfter
     */
    
    private void shiftNodes(Long shiftAfter, NestedSetTree tree){
        final long inc = 2;
        shiftRight(inc, shiftAfter, tree.getId());
        shiftLeft(inc, shiftAfter, tree.getId());
    }

    public N add(N newNode, T tree){
        return add(newNode, null, tree);
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
    public N add(N newNode, N parent, T tree) {
        if (parent == null) {
            // insert directly under root
            N root = findRoot(tree);
            if (root == null) {
                // no root was found, we are inserting root now!!
                newNode.setParent(null);
                newNode.setLeft(1L);
                newNode.setRight(2L);
                // set the tree it belongs to
                newNode.setTree(tree);
                logger.info(" Creating NEW a root node ");
            } else {
                throw new IllegalStateException("No parent was specified and Root node already exists");
            }
        } else {
            // We always need to know the parent before adding a node
            Long newLeft = parent.getLeft() + 1;
            N rm = getRightMostNodeFor(parent);
            if (rm != null) {
                // This should only happen when adding nodes under a parent that already has children
                // So we need to calculate the right most one to add next to it
                newLeft = rm.getRight() + 1;
            }

            long newRight = newLeft + 1;
            logger.info(String.format("New left is %d & New right is %d ",newLeft, newRight));

            newNode.setParent(parent.getId());
            newNode.setLeft(newLeft);
            newNode.setRight(newRight);

            // set the tree it belongs to
            newNode.setTree(tree);

            Long shiftAfter = newLeft - 1;
            logger.info(" shifting nodes after: "+shiftAfter );
            shiftNodes(shiftAfter, tree);
        }
        save(newNode);
        // update depth if required
        // forces a commit, do not remove!
        flush();
        return newNode;
    }

    public List<N> getTree(Long rootId){
        String clazzName = getPersistentClass().getSimpleName();
        String ql =
                String.format(
                     " SELECT node FROM %s node, %s parent " +
                     " WHERE node.left > parent.left AND  node.left < parent.right " +
                     " AND parent.id = :id ORDER BY node.left", clazzName, clazzName);

        Query query = getEntityManager().createQuery(ql, getPersistentClass());
        query.setParameter("id", rootId);
        @SuppressWarnings("unchecked")
        List<N> list = query.getResultList();
        return list;
    }


    public List<N> getLeafNodes(){
        String clazzName = getPersistentClass().getSimpleName();
       /*
        *  SELECT * FROM NestedSetNode WHERE lft = (rgt - 1);
        *  (rgt – lft) = 1
        */
        String ql = String.format(
                " SELECT node FROM %s node " +
                " WHERE  ( node.right = node.left ) = 1 ", clazzName);
        Query query = getEntityManager().createQuery(ql, getPersistentClass());
        @SuppressWarnings("unchecked")
        List<N> list = query.getResultList();
        return list;
    }

    /**
     * Go get me the right most node, below this Parent node
     * @param parent
     * @return
     */
    public N getRightMostNodeFor(N parent){
        logger.debug(" getting the right-most node under: "+parent);
        String clazzName = getPersistentClass().getSimpleName();
        N rm = null;
        String q1 = String.format(
                " SELECT max(n.right) FROM %s n WHERE n.left > :lft AND n.right < :rgt ",
                clazzName);
        Query query1 = getEntityManager().createQuery(q1, Number.class);

        try{
            query1.setParameter("lft",parent.getLeft());
            query1.setParameter("rgt",parent.getRight());
            Number max = (Number)query1.getSingleResult();
            if(max != null){
                String q2 = String.format(
                        " SELECT n FROM %s n WHERE n.right = :max ",
                        clazzName);
                Query query2 = getEntityManager().createQuery(q2, getPersistentClass());
                query2.setParameter("max",max);

                rm  = getPersistentClass().cast(query2.getSingleResult());
            }
        }catch (NoResultException nre) {
            logger.warn(" No Left Most Node was found for parent "+parent);
        }

        return rm;
    }

    public N getLeftMostNodeFor(N parent){
        N lm = null;
        String clazzName = getPersistentClass().getSimpleName();
        String q1 = String.format(" SELECT min(n.right) FROM %s n WHERE n.left > :lft AND n.right < :rgt ", clazzName);
        Query query1 = getEntityManager().createQuery(q1, Number.class);
        try{
            query1.setParameter("lft",parent.getLeft());
            query1.setParameter("rgt",parent.getRight());
            Number min = (Number)query1.getSingleResult();
            if(min != null){
                String q2 = String.format(" SELECT n FROM %s n WHERE n.left = :min ", clazzName);
                Query query2 = getEntityManager().createQuery(q2, getPersistentClass());
                query2.setParameter("min",min);
                lm  = getPersistentClass().cast(query2.getSingleResult());
            }
        }catch (NoResultException nre) {
            logger.warn(" No Left Most Node was found for parent "+parent);
        }

        return lm;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(N node, T tree){
        Long width = node.getRight() - node.getLeft() + 1;
        Long l = node.getLeft();
        Long r = node.getRight();
        String clazzName = getPersistentClass().getSimpleName();
        String delete =  String.format(" DELETE FROM %s n WHERE n.left BETWEEN :l AND :r ",clazzName);
        Query deleteQuery = getEntityManager().createQuery(delete);
        deleteQuery.setParameter("l",l);
        deleteQuery.setParameter("r",r);
        int rows = deleteQuery.executeUpdate();
        flush();
        final long inc = -1 * width;
        int sr = shiftRight(inc, r, tree.getId());
        int sl = shiftLeft(inc, l, tree.getId());

        logger.debug(" shift right " + sr);
        logger.debug(" shift left " + sl);
        return (rows > 0);
    }

}
