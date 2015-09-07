package com.faraya.legioss.core.dao.costing;

import com.faraya.legioss.core.dao.ns.NestedSetTreeDAO;
import com.faraya.legioss.core.entity.costing.ActivityTree;
import org.springframework.stereotype.Repository;


@Repository
public class ActivityTreeDAO extends NestedSetTreeDAO<ActivityTree> implements IActivityTreeDAO {

    public ActivityTreeDAO() {
        super(ActivityTree.class);
    }

}
