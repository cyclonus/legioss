package com.faraya.legioss.core.entity.accounting;

import com.faraya.legioss.core.entity.AbstractEntity;

import javax.persistence.*;

/**
 * Created by fabrizzio on 4/24/15.
 */

@Entity
@Table(name = "currency",
        indexes =  {
                @Index(name = "name", unique = true, columnList = "name"),
                @Index(name = "symbol", unique = true, columnList = "symbol")
        }
)
public class Currency extends AbstractEntity {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "symbol", nullable = false)
    private String symbol;


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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;

        Currency currency = (Currency) o;

        if (!getId().equals(currency.getId())) return false;
        return getSymbol().equals(currency.getSymbol());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getSymbol().hashCode();
        return result;
    }
}
