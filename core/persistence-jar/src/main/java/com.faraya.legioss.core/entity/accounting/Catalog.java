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
public class Catalog extends NestedSetTree<AccountNode> {

    public Catalog() {
    }

    public Catalog(String name) {
        super(name);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Catalog)) return false;
        Catalog catalog = (Catalog) o;
        return (
          getId().compareTo(catalog.getId()) == 0 &&
            getName().equals(catalog.getName())
        );
    }
}
