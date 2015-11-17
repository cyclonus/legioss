package com.faraya.legioss.core.entity.payroll.agreement;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.common.Money;
import com.faraya.legioss.core.entity.common.Period;
import com.faraya.legioss.core.entity.costing.Piecework;

import javax.persistence.*;

/**
 *
 * Created by fabrizzio on 10/11/15.
 */

@Entity
@Table(name = "payroll_piecework_agreement",
        indexes =  {
        }
)
public class PieceworkAgreement extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Period period;

    @Embedded
    private Money money;

    @JoinColumn(name = "piecework_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    Piecework piecework;

    public PieceworkAgreement(Period period, Money money, Piecework piecework) {
        this.period = period;
        this.money = money;
        this.piecework = piecework;
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

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public Piecework getPiecework() {
        return piecework;
    }

    public void setPiecework(Piecework piecework) {
        this.piecework = piecework;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PieceworkAgreement that = (PieceworkAgreement) o;

        if (!id.equals(that.id)) return false;
        if (!period.equals(that.period)) return false;
        if (!money.equals(that.money)) return false;
        return piecework.equals(that.piecework);

    }

    @Override
    public int hashCode() {
        int result = ( id == null ? 1 : id.hashCode());
        result = 31 * result + period.hashCode();
        result = 31 * result + money.hashCode();
        result = 31 * result + piecework.hashCode();
        return result;
    }
}
