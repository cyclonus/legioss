package com.faraya.legioss.core.entity.accounting;

import com.faraya.legioss.core.entity.AbstractEntity;

import javax.persistence.*;

/**
 *
 * Created by fabrizzio on 4/24/15.
 */

@Entity
@Table(name = "account",
        indexes =  {
                @Index(name = "name", unique = true, columnList = "name")
        }
)
public class Account extends AbstractEntity {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String i18eKey;

    private boolean active;

    @JoinColumn(name = "catalog_id", nullable = true)
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private AccountCatalog catalog;

    @JoinColumn(name = "currency_id", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Currency currency;

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

    public AccountCatalog getCatalog() {
        return catalog;
    }

    public void setCatalog(AccountCatalog catalog) {
        this.catalog = catalog;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }


}
