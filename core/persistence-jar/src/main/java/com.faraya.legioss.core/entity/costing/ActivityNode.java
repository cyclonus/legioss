package com.faraya.legioss.core.entity.costing;

import com.faraya.legioss.core.entity.accounting.*;
import com.faraya.legioss.core.entity.accounting.Catalog;
import com.faraya.legioss.core.entity.ns.NestedSetNode;

import javax.persistence.*;

@Entity
@Table(name = "activity_node",
        indexes =  {
                @Index(name = "tree", columnList = "tree_id"),
                @Index(name = "name", unique = true, columnList = "name, tree_id"),
                @Index(name = "left_index", unique = true, columnList = "left_value, tree_id"),
                @Index(name = "right_index", unique = true, columnList = "right_value, tree_id")
        }
)
public class ActivityNode extends NestedSetNode<Catalog> {

    public ActivityNode() {
    }

    public ActivityNode(String name) {
        super(name);
    }

    public ActivityNode(String name, Long parent) {
        super(name, parent);
    }

    public ActivityNode(Account account, Long parent) {
        this(account.getName(), parent);
        this.account = account;
    }

    /**
     * Can be Nullable because of Parent nodes
     */
    @JoinColumn(name = "activity_id", nullable = true)
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isParentNode(){
        return (account == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountNode)) return false;

        AccountNode that = (AccountNode) o;
        return getId().equals(that.getId())
                && getAccount() != null
                && getAccount().equals(that.getAccount());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getAccount().hashCode();
        return result;
    }

    @Override
    public String toString() {
        long childrenCount = 0;
        if(getLeft() != null && getRight() != null) {
            childrenCount = countChildren();
        }

        String account = (getAccount() != null  ? getAccount().getName() : "no-account" );
        String catalog = (getTree() != null ? getTree().getName() : "no-catalog" );

        return "ActivityNode{" +
                " id=" + getId() +
                ", name='" + getName() + '\'' +
                ", left=" + getLeft() +
                ", right=" + getRight() +
                ", parent=" + getParent() +
                ", account='" + account + '\'' +
                ", catalog='" + catalog + '\'' +
                ", children=" + childrenCount +
                '}';
    }
}

