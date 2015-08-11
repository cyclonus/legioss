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

public class UserImpl extends User {

    public UserImpl() {
    }

    public UserImpl(String email, String firstName, String lastName ,String password, String salt) {
        super(email, firstName, lastName ,password, salt);
    }
}
