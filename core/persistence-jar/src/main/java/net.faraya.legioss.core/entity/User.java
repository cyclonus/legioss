package net.faraya.legioss.core.entity;

import net.faraya.legioss.core.IIdentifiable;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author fab
 */

@MappedSuperclass
public abstract class User extends AbstractEntity implements IIdentifiable<Long>, Serializable {

    @Id
    @Column(name = "user_id", nullable = false)
    @SequenceGenerator(name="user_gen", sequenceName="user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_gen")
    private Long id;

    @Basic(optional = false)
    @Column(name = "name", unique=true , nullable = false, length = 50)
    private String name;

    @Basic(optional = false)
    @Column(name = "first_name", unique=true , nullable = false, length = 50)
    private String firstName;

    @Basic(optional = false)
    @Column(name = "last_name", unique=true , nullable = false, length = 50)
    private String lastName;

    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 250)
    private String password;

    public enum Status {
        ACTIVE,
        INACTIVE
    }

    @Column(name = "status", unique=true , nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE ;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
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

    @Override
    public boolean isTransient() {
        return (id == null);
    }


}
