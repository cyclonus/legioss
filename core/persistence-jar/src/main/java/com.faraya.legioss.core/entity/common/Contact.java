package com.faraya.legioss.core.entity.common;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;

import javax.persistence.*;

/**
 * User: fabrizzio
 * Date: 10/10/13
 * Time: 11:40 PM
 */
@Entity
@Table(name = "contact")
public class Contact extends AbstractEntity implements IIdentifiable<Long>{

    public enum Type {
        PHONE,
        MOBILE,
        SKYPE,
        FACEBOOK,
        LINKEDIN,
        EMAIL,
        OTHER
    }

    public Contact() {
    }

    public Contact(String value, Type type) {
        this.value = value;
        this.type = type;
    }

    public Contact(Type type, String value, int weight) {
        this.type = type;
        this.value = value;
        this.weight = weight;
    }

    @Column(name = "weight", nullable = false)
    private int weight = 0;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false, length=100)
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
