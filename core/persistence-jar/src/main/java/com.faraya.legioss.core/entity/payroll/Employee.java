package com.faraya.legioss.core.entity.payroll;

import com.faraya.legioss.core.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fabrizzio on 9/8/15.
 */

@Entity
@Table(name = "payroll_employee")
public class Employee extends AbstractEntity{

    public static final String NO_SECURITY_ID = "0";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "social_sec_number", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String socialSecurityNumber = NO_SECURITY_ID;

    @Column(name = "hired_date", nullable = false)
    private Date hireDate;

    @Column(name = "cease_date", nullable = true)
    private Date ceaseDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getCeaseDate() {
        return ceaseDate;
    }

    public void setCeaseDate(Date ceaseDate) {
        this.ceaseDate = ceaseDate;
    }

    @Override
    public boolean isTransient() {
        return (getId() == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!getId().equals(employee.getId())) return false;
        if (!getSocialSecurityNumber().equals(employee.getSocialSecurityNumber())) return false;
        if (!getHireDate().equals(employee.getHireDate())) return false;
        return !(getCeaseDate() != null ? !getCeaseDate().equals(employee.getCeaseDate()) : employee.getCeaseDate() != null);

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getSocialSecurityNumber().hashCode();
        result = 31 * result + getHireDate().hashCode();
        result = 31 * result + (getCeaseDate() != null ? getCeaseDate().hashCode() : 0);
        return result;
    }
}
