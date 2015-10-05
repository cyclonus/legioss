package com.faraya.legioss.core.entity.common;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.security.IDomain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fabrizzio on 4/24/15.
 * Company / Business
 */

@Entity
@Table(name = "business",
        indexes =  {
                @Index(name = "name", unique = true, columnList = "name")
        }
)
public class Business extends AbstractEntity implements IIdentifiable<Long>, IDomain {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @JoinColumn(name = "currency_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Currency primaryCurrency;

    @JoinColumn(name = "address_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Address> address;

    @JoinColumn(name = "contact_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Contact> contacts;

    @Embedded
    private Period businessYear;

    public Business() {
    }

    public Business(String name) {
        this.name = name;
    }

    public Business(String name, Currency primaryCurrency) {
        this.name = name;
        this.primaryCurrency = primaryCurrency;
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

    public Currency getPrimaryCurrency() {
        return primaryCurrency;
    }

    public void setPrimaryCurrency(Currency primaryCurrency) {
        this.primaryCurrency = primaryCurrency;
    }

    public Set<Address> getAddress() {
        if(address == null){
           address = new HashSet<>();
        }
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    public void addAddress(Address a){
        getAddress().add(a);
    }

    public Set<Contact> getContacts() {
        if(contacts == null){
           contacts = new HashSet<>();
        }
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact c){
        getContacts().add(c);
    }

    public Period getBusinessYear() {
        return businessYear;
    }

    public void setBusinessYear(Period businessYear) {
        this.businessYear = businessYear;
    }

    @Override
    public boolean isTransient() {
        return (getId() == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Business business = (Business) o;

        if (!getId().equals(business.getId())) return false;
        return getName().equals(business.getName());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }

}
