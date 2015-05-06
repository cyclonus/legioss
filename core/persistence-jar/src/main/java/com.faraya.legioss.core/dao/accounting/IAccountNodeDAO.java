package com.faraya.legioss.core.dao.accounting;

import com.faraya.legioss.core.dao.ns.INestedSetDAO;
import com.faraya.legioss.core.entity.accounting.AccountCatalog;
import com.faraya.legioss.core.entity.accounting.AccountNode;

/**
 *
 * Created by fabrizzio on 4/29/15.
 */
public interface IAccountNodeDAO extends INestedSetDAO <AccountNode,AccountCatalog> {
}
