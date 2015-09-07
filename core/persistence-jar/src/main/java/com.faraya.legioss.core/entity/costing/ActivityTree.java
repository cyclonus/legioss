package com.faraya.legioss.core.entity.costing;

import com.faraya.legioss.core.entity.ns.NestedSetTree;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "activity_tree",
        indexes =  {
                @Index(name = "name", unique = true, columnList = "name")
        }
)
public class ActivityTree extends NestedSetTree<ActivityNode> {

    public ActivityTree() {
    }

    public ActivityTree(String name) {
        super(name);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityTree)) return false;
        ActivityTree activityTree = (ActivityTree) o;
        return (
                getId().compareTo(activityTree.getId()) == 0 &&
                        getName().equals(activityTree.getName())
        );
    }
}
