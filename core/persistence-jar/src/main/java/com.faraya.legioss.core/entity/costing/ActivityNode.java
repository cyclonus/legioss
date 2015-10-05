package com.faraya.legioss.core.entity.costing;

import com.faraya.legioss.core.entity.common.Business;
import com.faraya.legioss.core.entity.ns.NestedSetNode;
import javax.persistence.*;

@Entity
@Table(name = "activity_node",
        indexes =  {
                @Index(name = "tree", columnList = "tree_id"),
                @Index(name = "name", unique = true, columnList = "name, tree_id"),
                @Index(name = "left_index", unique = true, columnList = "left_value, tree_id"),
                @Index(name = "right_index", unique = true, columnList = "right_value, tree_id")
        }
)
public class ActivityNode extends NestedSetNode<ActivityTree> {

    public ActivityNode() {
    }

    public ActivityNode(String name) {
        super(name);
    }

    public ActivityNode(String name, Long parent) {
        super(name, parent);
    }

    public ActivityNode(Activity activity, Long parent, Business business) {
        this(activity.getName(), parent);
        this.activity = activity;
        this.business = business;
    }

    @JoinColumn(name = "activity_id", nullable = true)
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private Activity activity;

    @JoinColumn(name = "business_id", nullable = true)
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private Business business;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ActivityNode that = (ActivityNode) o;

        if (!activity.equals(that.activity)) return false;
        return business.equals(that.business);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + activity.hashCode();
        result = 31 * result + business.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ActivityNode{" +
                "activity=" + activity +
                ", business=" + business +
                '}';
    }
}

