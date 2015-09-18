package com.faraya.legioss.core.entity.security;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author fab
 */

@Entity
@Table(name = "security_user",
        indexes =  {
                @Index(name = "name", unique = true, columnList = "name,email")
        }
)
public class User extends AbstractEntity implements IIdentifiable<Long>, IUser {

    @Id
    @Column(name = "id", nullable = false)
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
    private String primaryEmail;

    @Column(name = "profile_pic_url", nullable = true, length=150)
    private String profilePicURL;

    @Column(name = "status", unique=true , nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE ;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name="user_role",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="role_id")})
    private Set<Role> roles;


    public User() {
    }

    public User(String primaryEmail, String firstName, String lastName) {
        this.primaryEmail = primaryEmail.toLowerCase();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail.toLowerCase();
    }

    public String getProfilePicURL() {
        return profilePicURL;
    }

    public void setProfilePicURL(String profilePicURL) {
        this.profilePicURL = profilePicURL;
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

    public boolean hasRole(String roleName){
        roleName = roleName.toLowerCase();
        Set<Role> roles = getRoles();
        for(Role r:roles){
            if(roleName.equals(r.getName())){
              return true;
            }
        }
        return false;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }


}
