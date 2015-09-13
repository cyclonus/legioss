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
@Table(name = "accounting_journal_entry")
public class JournalEntry extends AbstractEntity implements IIdentifiable<Long>{

    @Id
    @Column(name = "id", nullable = false)
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

    public TransactionJournal getTransactionJournal() {
        return transactionJournal;
    }

    public void setTransactionJournal(TransactionJournal transactionJournal) {
        this.transactionJournal = transactionJournal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BalanceType getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(BalanceType balanceType) {
        this.balanceType = balanceType;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean isTransient() {
        return id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JournalEntry entry = (JournalEntry) o;

        if (!getId().equals(entry.getId())) return false;
        if (!getTransactionJournal().equals(entry.getTransactionJournal())) return false;
        if (!getDate().equals(entry.getDate())) return false;
        if (!getAmount().equals(entry.getAmount())) return false;
        if (getBalanceType() != entry.getBalanceType()) return false;
        return getEventId().equals(entry.getEventId());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getTransactionJournal().hashCode();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getAmount().hashCode();
        result = 31 * result + getBalanceType().hashCode();
        result = 31 * result + getEventId().hashCode();
        return result;
    }
}
