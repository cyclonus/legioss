package com.faraya.legioss.core.entity.accounting;

import com.faraya.legioss.core.entity.ns.NestedSetNode;
import javax.persistence.*;

/**
 *
 * Created by fabrizzio on 4/29/15.
 */

@Entity
@Table(name = "account_node",
        indexes =  {
                @Index(name = "tree", columnList = "tree_id"),
               // @Index(name = "name", unique = true, columnList = "name, tree_id"),
                @Index(name = "left_index", unique = true, columnList = "left_value"),
                @Index(name = "right_index", unique = true, columnList = "right_value")
        }
)
public class AccountNode extends NestedSetNode <AccountCatalog> {

    public AccountNode() {
    }

    public AccountNode(String name) {
        super(name);
    }

    public AccountNode(String name, Long parent) {
        super(name, parent);
    }

    public AccountNode(Account account, Long parent) {
        this(account.getName(), parent);
        this.account = account;
    }

    /**
     * Can be Nullable because of Parent nodes
     */
    @JoinColumn(name = "account_id", nullable = true)
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


}
