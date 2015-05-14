package com.faraya.legioss.core.entity.accounting;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Created by fabrizzio on 5/13/15.
 */

@Entity
@Table(name = "transaction_journal")
public class TransactionJournal extends AbstractEntity implements IIdentifiable<Long>{

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    private Date date;

    @OneToMany(mappedBy="transactionJournal")
    private Set<JournalEntry> entries;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public TransactionJournal() {
    }

    public TransactionJournal(Account account, Date date) {
        this.account = account;
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<JournalEntry> getEntries() {
        if(entries == null){
           entries = new HashSet<>();
        }
        return entries;
    }

    public void setEntries(Set<JournalEntry> entries) {
        this.entries = entries;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionJournal that = (TransactionJournal) o;

        if (!getId().equals(that.getId())) return false;
        if (!getAccount().equals(that.getAccount())) return false;
        return getDate().equals(that.getDate());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getAccount().hashCode();
        result = 31 * result + getDate().hashCode();
        return result;
    }
}
