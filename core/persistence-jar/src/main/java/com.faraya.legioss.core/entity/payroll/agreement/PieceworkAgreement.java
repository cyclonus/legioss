package com.faraya.legioss.core.entity.payroll.agreement;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.common.BasicMoney;
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
    private Period validity;

    @Column(name = "active", nullable = true)
    private boolean active;

    @Embedded
    private BasicMoney rate;

    @JoinColumn(name = "piecework_id", nullable = false, updatable = false, insertable = false)
    @OneToOne(fetch = FetchType.EAGER)
    Piecework piecework;

    @Column(name = "piecework_id", nullable = true )
    private Long pieceworkId;

    public PieceworkAgreement(Period validity, BasicMoney rate, Long pieceworkId) {
        this.validity = validity;
        this.rate = rate;
        this.pieceworkId = pieceworkId;
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
        if (!validity.equals(that.validity)) return false;
        if (!rate.equals(that.rate)) return false;
        return piecework.equals(that.piecework);

    }

    @Override
    public int hashCode() {
        int result = ( id == null ? 1 : id.hashCode());
        result = 31 * result + validity.hashCode();
        result = 31 * result + rate.hashCode();
        result = 31 * result + pieceworkId.hashCode();
        return result;
    }
}
