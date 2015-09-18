package com.faraya.legioss.core.entity.security;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Created by fabrizzio on 9/13/15.
 *
 * http://www.mkyong.com/hibernate/hibernate-many-to-many-example-join-table-extra-column-annotation/
 */

@MappedSuperclass
public abstract class UserDomain <T extends IDomain> implements Serializable{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "domain_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    protected T domain;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    protected User user;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name="user_role",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="role_id")})
    private Set<Role> roles;

    public UserDomain() {
    }

    public UserDomain(User user, T domain) {
        this.user = user;
        this.domain = domain;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public T getDomain() {
        return domain;
    }

    public void setDomain(T domain) {
        this.domain = domain;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        if(roles == null){
           roles = new HashSet<>();
        }
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        getRoles().add(role);
    }

    public void removeRole(Role role){
        getRoles().remove(role);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDomain<?> that = (UserDomain<?>) o;

        if (!getId().equals(that.getId())) return false;
        if (!getDomain().equals(that.getDomain())) return false;
        return getUser().equals(that.getUser());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDomain().hashCode();
        result = 31 * result + getUser().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName()+ "{" +
                "id=" + id +
                ", domain=" + domain +
                ", user=" + user +
                ", enabled=" + enabled +
                '}';
    }
}
