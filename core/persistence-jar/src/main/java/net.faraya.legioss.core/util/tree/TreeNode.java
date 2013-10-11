package net.faraya.legioss.core.util.tree;

import net.faraya.legioss.core.IIdentifiable;

import java.util.ArrayList;
import java.util.List;

/**
 * User: fabrizzio
 * Date: 8/6/13
 * Time: 10:45 PM
 *
 */
public class TreeNode implements IIdentifiable<Long>{

    private Long id;

    private String name;

    private List<TreeNode> children;

    private TreeNode parent;

    public TreeNode() {
    }

    public TreeNode(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TreeNode(Long id, String name, TreeNode parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        if(children == null){
           children = new ArrayList<TreeNode>();
        }
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public void addChild(TreeNode node){
        getChildren().add(node);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
