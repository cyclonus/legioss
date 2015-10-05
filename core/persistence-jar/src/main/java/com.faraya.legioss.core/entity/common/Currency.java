package com.faraya.legioss.core.entity.common;

import com.faraya.legioss.core.IIdentifiable;
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
public class Currency extends AbstractEntity implements IIdentifiable<Long>{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "short_name", nullable = false, unique = true, length = 3)
    private String shortName;

    @Column(name = "symbol", nullable = false, unique = true, length = 1)
    private String symbol;

    public Currency() {

    }

    public Currency(String name, String shortName, String symbol) {
        this.name = name;
        this.shortName = shortName;
        this.symbol = symbol;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        if (!id.equals(currency.id)) return false;
        if (!name.equals(currency.name)) return false;
        if (!shortName.equals(currency.shortName)) return false;
        return symbol.equals(currency.symbol);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + shortName.hashCode();
        result = 31 * result + symbol.hashCode();
        return result;
    }
}
