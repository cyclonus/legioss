package com.faraya.legioss.core.entity.payroll.chart;

import com.faraya.legioss.core.entity.common.Business;
import com.faraya.legioss.core.entity.ns.NestedSetNode;
import com.faraya.legioss.core.entity.payroll.Employee;

import javax.persistence.*;

/**
 *
 * Created by fabrizzio on 10/4/15.
 */

@Entity
@Table(name = "payroll_org_chart_node",
        indexes =  {
                @Index(name = "tree", columnList = "tree_id"),
                @Index(name = "name", unique = true, columnList = "name, tree_id"),
                @Index(name = "left_index", unique = true, columnList = "left_value, tree_id"),
                @Index(name = "right_index", unique = true, columnList = "right_value, tree_id")
        }
)
public class ChartNode extends NestedSetNode <OrgChart> {

    public ChartNode() {
    }

    public ChartNode(Employee employee, String position, Long parent, Business business) {
        super(position, parent);
        this.employee = employee;
        this.business = business;
    }

    public ChartNode(Employee employee, String position, Business business) {
        this(employee, position, null, business);
    }

    public ChartNode(Business business) {
        this(null, business.getName(), null, business);
    }

    /**
     * Can be Nullable because of Parent nodes
     */
    @JoinColumn(name = "employee_id", insertable = false, updatable = false )
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private Employee employee;

    @Column(name = "employee_id", nullable = true)
    private Long employeeId;

    @JoinColumn(name = "business_id" )
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private Business business;

    @Column(name = "business_id", nullable = true, insertable = false, updatable = false)
    private Long businessId;


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

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

    public boolean isParentNode(){
        return (employee == null);
    }


    @Override
    public String toString() {
        long childrenCount = 0;
        if(getLeft() != null && getRight() != null) {
            childrenCount = countChildren();
        }

        return "EmployeeNode{" +
                "  id=" + getId() +
                ", name='" + getName() + '\'' +
                ", left=" + getLeft() + '\'' +
                ", right=" + getRight() + '\'' +
                ", parent=" + getParent() + '\'' +
                ", employee='" + getEmployeeId() + '\'' +
                ", catalog='" + getTreeId() + '\'' +
                ", children=" + childrenCount + '\'' +
                '}';
    }
}
