package com.faraya.legioss.core.entity.accounting;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.model.accounting.BalanceType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 *  Created by fabrizzio on 5/12/15.
 */

@Entity
@Table(name = "journal_entry")
public class JournalEntry extends AbstractEntity implements IIdentifiable<Long>{

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="transaction_id")
    private TransactionJournal transactionJournal;

    private Date date;

    private BigDecimal amount;

    private BalanceType balanceType;

    private Long eventId;

    public JournalEntry() {
    }

    public JournalEntry(Date date, BigDecimal amount, BalanceType balanceType, Long eventId) {
        this.date = date;
        this.amount = amount;
        this.balanceType = balanceType;
        this.eventId = eventId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
       this.id = id;
    }

    @Override
    public boolean isTransient() {
        return id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JournalEntry that = (JournalEntry) o;

        if (!getId().equals(that.getId())) return false;
        if (!transactionJournal.equals(that.transactionJournal)) return false;
        if (!date.equals(that.date)) return false;
        if (!amount.equals(that.amount)) return false;
        if (balanceType != that.balanceType) return false;
        return eventId.equals(that.eventId);

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + transactionJournal.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + balanceType.hashCode();
        result = 31 * result + eventId.hashCode();
        return result;
    }
}
