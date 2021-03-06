package com.faraya.legioss.core.entity.costing;

import com.faraya.legioss.core.entity.common.Business;
import com.faraya.legioss.core.entity.ns.NestedSetTree;

import javax.persistence.*;

@Entity
@Table(name = "activity_tree",
        indexes =  {
                @Index(name = "name", unique = true, columnList = "name")
        }
)
public class ActivityTree extends NestedSetTree<ActivityNode> {

    public ActivityTree() {
    }

    public ActivityTree(Business business) {
        super(business.getName());
        this.business = business;
    }

    @JoinColumn(name = "business_id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Business business;

    @Column(name = "business_id", nullable = false)
    private Long businessId;

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ActivityTree that = (ActivityTree) o;

        return businessId.equals(that.businessId);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + businessId.hashCode();
        return result;
    }
}
