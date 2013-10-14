package net.faraya.legioss.core.entity.ns;

import net.faraya.legioss.core.IIdentifiable;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.util.StringTokenizer;

/**
 * NS stands for Nested Set
 * User: fabrizzio
 * Date: 6/8/13
 * Time: 5:34 PM
 */

@Entity
@Table(name = "ns_node")
public class Node implements IIdentifiable <Long> {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false,unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "lft", nullable = false)
    @Index(name="left_index")
    private Integer left;

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    @Column(name = "rgt", nullable = false)
    @Index(name="right_index")
    private  Integer right;

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public boolean isRoot(){
        return (getLeft() == 1);
    }

    private Long parent;

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public boolean isLeaf(){
        // lft value from the rgt value, the result must be 1.
        return ((getRight() - getLeft()) == 1);
    }

    public int countChildren(){
        return (((getRight()-1) - getLeft()) / 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (!id.equals(node.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode() * 11;
    }

    @Override
    public String toString() {
        return "Node{" +
                " id=" + id +
                ", name='" + name + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    //TODO Rewrite this method using Scanner
    public static Node fromString(String input) {
        //Node{ id=3, name='child-2', left=5, right=6}
        try {
            StringTokenizer st = new StringTokenizer(input, "{ },='");
            if (st.hasMoreTokens()) {
                Node n = new Node();
                while (st.hasMoreTokens()) {
                    String t = st.nextToken();// Node
                    if ("id".equals(t)) {
                        n.setId(Long.parseLong(st.nextToken()));
                        continue;
                    }
                    if ("name".equals(t)) {
                        n.setName(st.nextToken());
                        continue;
                    }
                    if ("left".equals(t)) {
                        n.setLeft(Integer.parseInt(st.nextToken()));
                        continue;
                    }
                    if ("right".equals(t)) {
                        n.setRight(Integer.parseInt(st.nextToken()));
                    }

                }
                return n;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
