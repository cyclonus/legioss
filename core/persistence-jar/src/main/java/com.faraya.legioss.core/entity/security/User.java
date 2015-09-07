package com.faraya.legioss.core.entity.security;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 *
 * Created by fabrizzio on 8/6/15.
 */

@Entity
@Table(name = "user",
        indexes =  {
                @Index(name = "name", unique = true, columnList = "name,email")
        }
)

public class User extends AbstractUser {

    public User() {
    }

    public User(String email, String firstName, String lastName) {
        super(email, firstName, lastName );
    }
}
