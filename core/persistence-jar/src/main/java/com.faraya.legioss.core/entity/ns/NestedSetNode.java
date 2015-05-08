package com.faraya.legioss.core.entity.ns;

import com.faraya.legioss.core.IIdentifiable;
//import org.hibernate.annotations.Index;
import javax.persistence.*;

/**
 * NS stands for Nested Set
 * User: fabrizzio
 * Date: 6/8/13
 * Time: 5:34 PM
 */

/*
@Entity
@Table(name = "ns_node",
        indexes =  {
                @Index(name = "tree", columnList = "tree_id"),
                @Index(name = "name", unique = true, columnList = "name, tree_id"),
                @Index(name = "left_index", unique = true, columnList = "left_value"),
                @Index(name = "right_index", unique = true, columnList = "right_value")
        }
)*/
@MappedSuperclass
public abstract class NestedSetNode <T extends NestedSetTree> implements IIdentifiable<Long> {

    public NestedSetNode() {
    }

    public NestedSetNode(String name) {
        this.name = name;
    }

    public NestedSetNode(String name, Long parent) {
        this.name = name;
        this.parent = parent;
    }

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JoinColumn(name = "tree_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private T tree;

    public T getTree() {
        return tree;
    }

    public void setTree(T tree) {
        this.tree = tree;
    }

    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "left_value", nullable = false)
    private Long left;

    public Long getLeft() {
        return left;
    }

    public void setLeft(Long left) {
        this.left = left;
    }

    @Column(name = "right_value", nullable = false)
    private Long right;

    public Long getRight() {
        return right;
    }

    public void setRight(Long right) {
        this.right = right;
    }

    public boolean isRoot() {
        return (  getLeft() == 1);
    }

    private Long parent;

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public boolean isLeaf() {
        // lft value from the rgt value, the result must be 1.
        return ((getRight() - getLeft()) == 1);
    }

    public long countChildren() {
        // ((11 – 1) – 4) / 2 = 3 nodes
        return ((getRight() - 1) - getLeft()) / 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NestedSetNode node = (NestedSetNode) o;

        if (!id.equals(node.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 *  result * id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        long childrenCount = 0;
        if(getLeft() != null && getRight() != null) {
            childrenCount = countChildren();
        }
        return "Node{" +
                " id=" + id +
                ", name='" + name + '\'' +
                ", left=" + left +
                ", right=" + right +
                ", parent=" + parent +
                ", children=" + childrenCount +
                '}';
    }

}
