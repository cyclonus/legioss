package com.faraya.legioss.core.entity.payroll.agreement;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.payroll.Employee;

import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PiceworkAgreement> piceworkAgreements;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<HoursAgreement> hoursAgreements;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

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

    public Set<PiceworkAgreement> getPiceworkAgreements() {
        if(piceworkAgreements == null){
           piceworkAgreements = new HashSet<>();
        }
        return piceworkAgreements;
    }

    public void setPiceworkAgreements(Set<PiceworkAgreement> piceworkAgreements) {
        this.piceworkAgreements = piceworkAgreements;
    }

    public void addPieceworkAgreement(PiceworkAgreement piceworkAgreement){
         getPiceworkAgreements().add(piceworkAgreement);
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
