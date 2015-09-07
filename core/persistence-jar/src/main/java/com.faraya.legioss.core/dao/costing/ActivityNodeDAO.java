package com.faraya.legioss.core.dao.costing;

import com.faraya.legioss.core.dao.ns.NestedSetDAO;
import com.faraya.legioss.core.entity.costing.ActivityNode;
import com.faraya.legioss.core.entity.costing.ActivityTree;
import org.springframework.stereotype.Repository;


@Repository
public class ActivityNodeDAO extends NestedSetDAO<ActivityNode,ActivityTree> implements IActivityNodeDAO {

    public ActivityNodeDAO() {
        super(ActivityNode.class);
    }

}
