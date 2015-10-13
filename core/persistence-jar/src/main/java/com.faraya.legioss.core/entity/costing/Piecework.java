package com.faraya.legioss.core.entity.costing;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;

import javax.persistence.*;

/**
 *
 * Created by fabrizzio on 10/11/15.
 */

@Entity
@Table(name = "costing_piecework",
        indexes =  {
                @Index(name = "code", unique = true, columnList = "code"),
                @Index(name = "name", unique = true, columnList = "name")
        }
)
public class Piecework extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "description", nullable = true, unique = true, length = 50)
    private String description;

    public Piecework(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Piecework(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piecework piecework = (Piecework) o;

        if (!id.equals(piecework.id)) return false;
        if (!code.equals(piecework.code)) return false;
        return name.equals(piecework.name);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }
}
