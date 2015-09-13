package com.faraya.legioss.core.entity.security;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * http://springinpractice.com/2010/10/27/quick-tip-spring-security-role-based-authorization-and-permissions
 * <p>
 * http://www.mkyong.com/hibernate/hibernate-many-to-many-example-join-table-extra-column-annotation/
 * Created by fabrizzio on 8/3/15.
 */

@Entity
@Table(name = "security_role",
        indexes = {
                @Index(name = "role_name", columnList = "name")
        }
)
public class Role extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name="role_permission",
            joinColumns={@JoinColumn(name="role_id")},
            inverseJoinColumns={@JoinColumn(name="permission_id")})
    private Set<Permission> permissions;

    public Role(String name) {
        this.name = name.toLowerCase();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        if(permissions == null){
           permissions = new HashSet<>();
        }
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(Permission permission){
        getPermissions().add(permission);
    }

    public void removePermission(Permission permission){
        getPermissions().remove(permission);
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }
}
