package com.faraya.legioss.core.entity.accounting;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.common.Currency;
import com.faraya.legioss.core.model.accounting.AccountType;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Created by fabrizzio on 4/24/15.
 */

@Entity
@Table(name = "account",
        indexes =  {
                @Index(name = "name", unique = true, columnList = "name,catalog_id")
        }
)
public class Account extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private AccountType accountType;

    private String i18eKey;

    private boolean active;

    @JoinColumn(name = "catalog_id", nullable = false)
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Catalog catalog;

    @JoinColumn(name = "currency_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Currency currency;

    @JoinColumn(name = "transaction_id", nullable = true)
    @OneToMany()
    private Set<TransactionJournal> posting;

    public Account() {
    }

    public Account(String name, AccountType type, Currency currency, Catalog catalog) {
        this.name = name;
        this.accountType = type;
        this.currency = currency;
        this.catalog = catalog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getI18eKey() {
        return i18eKey;
    }

    public void setI18eKey(String i18eKey) {
        this.i18eKey = i18eKey;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Set<TransactionJournal> getPosting() {
        if(posting == null){
           posting = new HashSet<>();
        }
        return posting;
    }

    public void setPosting(Set<TransactionJournal> posting) {
        this.posting = posting;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (!getId().equals(account.getId())) return false;
        if (!getName().equals(account.getName())) return false;
        if (!getCatalog().equals(account.getCatalog())) return false;
        return getCurrency().equals(account.getCurrency());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getCatalog().hashCode();
        result = 31 * result + getCurrency().hashCode();
        return result;
    }
}
