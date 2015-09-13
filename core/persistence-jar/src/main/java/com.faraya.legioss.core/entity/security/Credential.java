package com.faraya.legioss.core.entity.security;

import com.faraya.legioss.core.IIdentifiable;

import javax.persistence.*;


@Entity
@Table(name = "security_hashed_credential",
        indexes = {

        }
)
public class Credential implements IIdentifiable <Long>{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(name = "password", nullable=false, length = 150)
    private String password;

    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne(optional = true, fetch = FetchType.LAZY)
    private User owner;

    public Credential() {
    }

    public Credential(User owner, String password) {
        this.owner = owner;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credential that = (Credential) o;

        if (!getId().equals(that.getId())) return false;
        return getOwner().equals(that.getOwner());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getOwner().hashCode();
        return result;
    }
}
