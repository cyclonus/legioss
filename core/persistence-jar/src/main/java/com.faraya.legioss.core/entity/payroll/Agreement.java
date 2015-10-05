package com.faraya.legioss.core.entity.payroll;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.payroll.Employee;

import javax.persistence.*;

/**
 *
 * Created by fabrizzio on 9/27/15.
 */

@Entity
@Table(name = "agreement",
        indexes =  {
        }
)
public class Agreement extends AbstractEntity implements IIdentifiable<Long> {
    //Set<HoursAgreement> // regular, overnight, weekend, holiday
    //Set<PiceworkAgreement>
    //FixedSalaryAgreement

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "employee_id", nullable = false,  insertable = false, updatable = false)
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private Employee employee;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isTransient() {
        return (getId() == null);
    }

}
