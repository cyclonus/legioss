package com.faraya.legioss.core.dao.accounting;

import com.faraya.legioss.core.dao.ns.NestedSetTreeDAO;
import com.faraya.legioss.core.entity.accounting.AccountCatalog;
import org.springframework.stereotype.Repository;

/**
 *
 * Created by fabrizzio on 4/29/15.
 */

@Repository
public class AccountCatalogDAO extends NestedSetTreeDAO<AccountCatalog> implements IAccountCatalogDAO{

    public AccountCatalogDAO() {
        super(AccountCatalog.class);
    }

}
