package com.faraya.legioss.core.entity.payroll.agreement;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.common.Money;
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

    @Embedded
    private Period period;

    private HoursAgreementType hoursAgreementType;

    @Embedded
    private Money money;


    //ProjectID | TaskID 

    public HoursAgreement() {
    }

    public HoursAgreement(Period period, HoursAgreementType hoursAgreementType, Money money) {
        this.period = period;
        this.hoursAgreementType = hoursAgreementType;
        this.money = money;
    }

    public HoursAgreement(Period period, Money money) {
        this(period, HoursAgreementType.REGULAR, money);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public HoursAgreementType getHoursAgreementType() {
        return hoursAgreementType;
    }

    public void setHoursAgreementType(HoursAgreementType hoursAgreementType) {
        this.hoursAgreementType = hoursAgreementType;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }

    @Override
    public String toString() {
        return "HoursAgreement{" +
                "id=" + id +
                ", period=" + period +
                ", hoursAgreementType=" + hoursAgreementType +
                ", money=" + money +
                '}';
    }
}
