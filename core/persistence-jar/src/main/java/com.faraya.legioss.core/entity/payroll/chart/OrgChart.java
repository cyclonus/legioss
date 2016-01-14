package com.faraya.legioss.core.entity.payroll.chart;

import com.faraya.legioss.core.entity.common.Business;
import com.faraya.legioss.core.entity.ns.NestedSetTree;

import javax.persistence.*;

/**
 *
 * Created by fabrizzio on 10/4/15.
 */
@Entity
@Table(name = "payroll_org_chart",
        indexes =  {
                @Index(name = "name", unique = true, columnList = "name")
        }
)
public class OrgChart extends NestedSetTree<ChartNode> {

    public OrgChart() {
    }

    public OrgChart(Business business) {
        super(business.getName());
        this.business = business;
    }

    @JoinColumn(name = "business_id", nullable = false, insertable = false, updatable = false )
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Business business;

    @Column(name = "business_id")
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


}
