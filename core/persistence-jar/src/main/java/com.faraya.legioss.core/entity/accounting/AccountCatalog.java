package com.faraya.legioss.core.entity.accounting;

import com.faraya.legioss.core.entity.ns.NestedSetTree;

import javax.persistence.*;

/**
 *
 * Created by fabrizzio on 4/29/15.
 */

@Entity
@Table(name = "account_catalog",
        indexes =  {
                @Index(name = "name", unique = true, columnList = "name")
        }
)
public class AccountCatalog extends NestedSetTree<AccountNode> {

    public AccountCatalog() {
    }

    public AccountCatalog(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountCatalog)) return false;

        NestedSetTree tree = AccountCatalog.class.cast(o);

        boolean sameName = getName().equals(tree.getName());
        boolean sameId = getId().equals(tree.getId());
        return sameName && sameId;

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
