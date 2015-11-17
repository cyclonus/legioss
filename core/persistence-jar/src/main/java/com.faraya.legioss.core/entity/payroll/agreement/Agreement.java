package com.faraya.legioss.core.entity.payroll.agreement;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.common.CalculationRule;
import com.faraya.legioss.core.entity.common.Money;
import com.faraya.legioss.core.entity.common.Workday;
import com.faraya.legioss.core.entity.payroll.Employee;

import javax.persistence.*;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Created by fabrizzio on 9/27/15.
 */

@Entity
@Table(name = "payroll_agreement",
        indexes =  {
        }
)
public class Agreement extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PieceworkAgreement> pieceworkAgreements;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<HoursAgreement> hoursAgreements;

    @Embedded
    private Money baseSalary; //Per period base salary.

    //Rule(("BaseSalary"), ("BaseSalary" + "workedHours"), ("PieceWork"), ("PieceWork" + "workedHours"), ("PieceWork + BaseSalary"), ("PieceWork + BaseSalary + workedHours"))
    //EnumSet<CalculationRule> rules;

    @OneToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Employee employee;

    @Column(name = "employee_id", updatable = false, insertable = false)
    private Long employeeId;

    //Jornada laboral (from 8-5)
    @Embedded
    private Workday workday;

    public Agreement() {
    }

    public Agreement(Workday workday, Money baseSalary) {
        this.workday = workday;
        this.baseSalary = baseSalary;
    }

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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Workday getWorkday() {
        return workday;
    }

    public void setWorkday(Workday workday) {
        this.workday = workday;
    }

    public Money getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Money baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Set<PieceworkAgreement> getPieceworkAgreements() {
        if(pieceworkAgreements == null){
           pieceworkAgreements = new HashSet<>();
        }
        return pieceworkAgreements;
    }

    public void setPieceworkAgreements(Set<PieceworkAgreement> pieceworkAgreements) {
        this.pieceworkAgreements = pieceworkAgreements;
    }

    public void addPieceworkAgreement(PieceworkAgreement pieceworkAgreement){
         getPieceworkAgreements().add(pieceworkAgreement);
    }

    public Set<HoursAgreement> getHoursAgreements() {
        if(hoursAgreements == null){
          hoursAgreements = new HashSet<>();
        }
        return hoursAgreements;
    }

    public void setHoursAgreements(Set<HoursAgreement> hoursAgreements) {
        this.hoursAgreements = hoursAgreements;
    }

    public void addHoursAgreements(HoursAgreement hoursAgreement){
       getHoursAgreements().add(hoursAgreement);
    }

    public boolean isTransient() {
        return (getId() == null);
    }


}
