package com.faraya.legioss.core.dao.accounting;

import com.faraya.legioss.core.dao.ns.NestedSetTreeDAO;
import com.faraya.legioss.core.entity.accounting.Catalog;
import org.springframework.stereotype.Repository;

/**
 *
 * Created by fabrizzio on 4/29/15.
 */

@Repository
public class CatalogDAO extends NestedSetTreeDAO<Catalog> implements ICatalogDAO {

    public CatalogDAO() {
        super(Catalog.class);
    }

}
