package com.faraya.legioss.core.entity.profile;

import com.faraya.legioss.core.entity.AbstractEntity;

import javax.persistence.*;

/**
 * User: fabrizzio
 * Date: 10/10/13
 * Time: 11:40 PM
 */
public class Contact extends AbstractEntity {

    public enum Type {
        PHONE,
        MOBILE,
        EMAIL,
        OTHER
    }

    @Id
    @Column(name = "contact_id", nullable = false)
    @SequenceGenerator(name="contact_gen", sequenceName="contact_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="contact_gen")
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "name", nullable = false, length=100)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!id.equals(contact.id)) return false;
        if (type != contact.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
