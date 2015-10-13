package com.faraya.legioss.core.entity.common;

/**
 * User: fabrizzio
 * Date: 10/10/13
 * Time: 10:46 PM
 */
import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address extends AbstractEntity implements IIdentifiable<Long>{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country", nullable = false, length=5)
    private String country;

    @Column(name = "street", nullable = false, length=250)
    private String street;

    @Column(name = "city", nullable = false, length=50)
    private String city;

    @Column(name = "state", nullable = false, length=50)
    private String state;

    @Column(name = "zipcode", nullable = false, length=10)
    private String zipCode;

    @Column(name = "weight", nullable = false)
    private int weight = 0;

    public Address() {
    }

    public Address(String country, String street, String city, String state, String zipCode) {
        this.country = country;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public Address(String country, String street, String city, String state, String zipCode, int weight) {
        this.country = country;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (weight != address.weight) return false;
        if (id != null ? !id.equals(address.id) : address.id != null) return false;
        if (!country.equals(address.country)) return false;
        if (!street.equals(address.street)) return false;
        if (!city.equals(address.city)) return false;
        if (!state.equals(address.state)) return false;
        return zipCode.equals(address.zipCode);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 1;
        result = 31 * result + country.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + zipCode.hashCode();
        result = 31 * result + weight;
        return result;
    }
}