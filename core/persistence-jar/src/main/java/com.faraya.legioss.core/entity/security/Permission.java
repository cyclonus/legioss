package com.faraya.legioss.core.entity.security;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;

import javax.persistence.*;

/**
 *
 * Created by fabrizzio on 8/3/15.
 */

@Entity
@Table(name = "security_permission",
        indexes = {
                @Index(name = "permission_name", columnList = "name")
        }
)
public class Permission extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    public Permission() {
    }

    public Permission(String name) {
        this.name = name;
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

    @Override
    public boolean isTransient() {
        return (id == null);
    }
}
