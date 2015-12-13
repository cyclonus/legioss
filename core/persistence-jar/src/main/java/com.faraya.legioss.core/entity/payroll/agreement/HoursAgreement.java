package com.faraya.legioss.core.entity.payroll.agreement;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.common.BasicMoney;
import com.faraya.legioss.core.entity.common.DailyWorkSchedule;
import com.faraya.legioss.core.entity.common.Period;

import javax.persistence.*;

/**
 *
 * Created by fabrizzio on 10/12/15.
 */

@Entity
public class HoursAgreement extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /*
     * Tells the system if the agreement is still valid
     * used also to keep historic log of how salaries change in time for employees
     */
    @Embedded
    private Period validity;

    @Column(name = "active", nullable = true)
    private boolean active;

    @Embedded
    private BasicMoney rate;

    //TODO: add a reference back to employee

    @Enumerated(EnumType.STRING)
    private PayType payType;

    @Embedded
    private DailyWorkSchedule schedule;

    @Column(name = "hours_shift", nullable = false, length = 2)
    private int hoursShift = 8;

    @Column(name = "project_ref", nullable = true, length = 50)
    private String projectRef;

    public HoursAgreement() {
    }

    public HoursAgreement(Period validity, BasicMoney rate) {
        this.validity = validity;
        this.rate = rate;
        this.active = validity.isOpen();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Period getValidity() {
        return validity;
    }

    public void setValidity(Period validity) {
        this.validity = validity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public BasicMoney getRate() {
        return rate;
    }

    public void setRate(BasicMoney rate) {
        this.rate = rate;
    }

    public DailyWorkSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(DailyWorkSchedule schedule) {
        this.schedule = schedule;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public int getHoursShift() {
        return hoursShift;
    }

    public void setHoursShift(int hoursShift) {
        this.hoursShift = hoursShift;
    }

    public String getProjectRef() {
        return projectRef;
    }

    public void setProjectRef(String projectRef) {
        this.projectRef = projectRef;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }

    /**
     * if the validity is updated and  set with an end of agreement we deactivate this instance
     */
    @PrePersist
    public void onPrePersist(){
       boolean val = getValidity().isOpen();
       setActive(val);
    }

    @Override
    public String toString() {
        return "HoursAgreement{" +
                "id=" + id +
                ", validity=" + validity +
                ", active=" + active +
                ", rate=" + rate +
                ", payType=" + payType +
                ", schedule=" + schedule +
                ", projectRef='" + projectRef + '\'' +
                '}';
    }
}
