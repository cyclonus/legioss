package net.faraya.legioss.core.entity.profile;

/**
 * User: fabrizzio
 * Date: 10/10/13
 * Time: 10:46 PM
 */
import net.faraya.legioss.core.IIdentifiable;
import net.faraya.legioss.core.entity.AbstractEntity;
import net.faraya.legioss.core.entity.profile.Profile;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ph_address")
public class Address extends AbstractEntity implements IIdentifiable<Long>, Serializable{

    @Id
    @Column(name = "address_id", nullable = false)
    @SequenceGenerator(name="address_gen", sequenceName="address_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="address_gen")
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String country;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Column(name = "address_country", nullable = false, length=5)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "address_street", nullable = false, length=250)
    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "address_city", nullable = false, length=50)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "address_state", nullable = false, length=50)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "address_zipcode", nullable = false, length=10)
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

}