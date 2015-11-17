package com.faraya.legioss.core.entity.payroll.log;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.costing.Piecework;
import com.faraya.legioss.core.entity.payroll.Employee;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * Created by fabrizzio on 11/5/15.
 */
@Entity
@Table(name = "piecework_log")
public class PieceworkLog extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "business_id", nullable = false)
    @OneToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Piecework piecework;

    @Column(name = "piecework_id", nullable = true, updatable = false, insertable = false)
    private Long pieceworkId;

    @Column(name = "date", nullable = false)
    private Date date;

    @JoinColumn(name = "employee_id", nullable = false)
    @OneToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Employee employee;

    @Column(name = "employee_id", nullable = false, updatable = false, insertable = false)
    private Long employeeId;

    @Column(name = "unit_count", nullable = false)
    private Integer unitCount;

    @JoinColumn(name = "signedby_id", nullable = false)
    @OneToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Employee signedBy;

    @Column(name = "signedby_id", nullable = true, updatable = false, insertable = false)
    private Long signedById;


    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Piecework getPiecework() {
        return piecework;
    }

    public void setPiecework(Piecework piecework) {
        this.piecework = piecework;
    }

    public Long getPieceworkId() {
        return pieceworkId;
    }

    public void setPieceworkId(Long pieceworkId) {
        this.pieceworkId = pieceworkId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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

    public Integer getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(Integer unitCount) {
        this.unitCount = unitCount;
    }

    public Employee getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(Employee signedBy) {
        this.signedBy = signedBy;
    }

    public Long getSignedById() {
        return signedById;
    }

    public void setSignedById(Long signedById) {
        this.signedById = signedById;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PieceworkLog that = (PieceworkLog) o;

        if (!id.equals(that.id)) return false;
        if (!piecework.equals(that.piecework)) return false;
        if (!pieceworkId.equals(that.pieceworkId)) return false;
        if (!date.equals(that.date)) return false;
        if (!employeeId.equals(that.employeeId)) return false;
        return unitCount.equals(that.unitCount);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + piecework.hashCode();
        result = 31 * result + pieceworkId.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + employeeId.hashCode();
        result = 31 * result + unitCount.hashCode();
        return result;
    }
}
