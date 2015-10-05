package com.faraya.legioss.core.entity.payroll.chart;

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

    public ChartNode(String position) {
        super(position);
    }

    public ChartNode(String position, Long parent) {
        super(position, parent);
    }

    /**
     * Can be Nullable because of Parent nodes
     */
    @JoinColumn(name = "employee_id", nullable = true, insertable = false, updatable = false)
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private Employee employee;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

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

    public boolean isParentNode(){
        return (employee == null);
    }


    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getEmployeeId().hashCode();
        return result;
    }

    @Override
    public String toString() {
        long childrenCount = 0;
        if(getLeft() != null && getRight() != null) {
            childrenCount = countChildren();
        }

        Long employeeId = (getEmployee() != null  ? getEmployee().getId() : 0L );
        String catalog = (getTree() != null ? getTree().getName() : "no-catalog" );

        return "EmployeeNode{" +
                " id=" + getId() +
                ", name='" + getName() + '\'' +
                ", left=" + getLeft() +
                ", right=" + getRight() +
                ", parent=" + getParent() +
                ", employee='" + employeeId + '\'' +
                ", catalog='" + catalog + '\'' +
                ", children=" + childrenCount +
                '}';
    }
}
