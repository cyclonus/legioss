package com.faraya.legioss.core.dao.accounting;

import com.faraya.legioss.core.dao.ns.NestedSetDAO;
import com.faraya.legioss.core.entity.accounting.Catalog;
import com.faraya.legioss.core.entity.accounting.AccountNode;
import org.springframework.stereotype.Repository;

/**
 *
 * Created by fabrizzio on 4/29/15.
 */

@Repository
public class AccountNodeDAO extends NestedSetDAO<AccountNode,Catalog> implements IAccountNodeDAO {

    public AccountNodeDAO() {
        super(AccountNode.class);
    }

}
