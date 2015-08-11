package com.faraya.legioss.core.entity.security;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.security.Role;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author fab
 */

@MappedSuperclass
public abstract class User extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = true)
    @Column(name = "name", unique=true , nullable=true, length = 60)
    private String name;

    @Basic(optional = false)
    @Column(name = "first_name", unique=true , nullable=false, length = 60)
    private String firstName;

    @Basic(optional = false)
    @Column(name = "last_name", unique=true , nullable=false, length = 60)
    private String lastName;

    @Basic(optional = false)
    @Column(name = "email", unique=true , nullable=false, length = 60)
    private String email;

    @Basic(optional = false)
    @Column(name = "password", nullable=false, length = 50)
    private String password;

    @Basic(optional = false)
    @Column(name = "salt", nullable=false, length = 100)
    private String salt;

    public enum Status {
        ACTIVE,
        INACTIVE
    }

    @Column(name = "status", unique=true , nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE ;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="user_role",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="role_id")})
    private Set<Role> roles;

    public User() {
    }

    public User(String email, String firstName, String lastName ,String password, String salt) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.salt = salt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public boolean isTransient() {
        return (id == null);
    }


}
