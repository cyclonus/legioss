package com.faraya.legioss.core.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 *
 * Created by fabrizzio on 8/6/15.
 */

@Entity
@Table(name = "security_user",
        indexes =  {
                @Index(name = "name", unique = true, columnList = "name,email")
        }
)

public class User extends AbstractUser {

    public User() {
    }

    @Column(name = "profile_pic_url", nullable = true, length=150)
    private String profilePicURL;

    public User(String email, String firstName, String lastName) {
        super(email, firstName, lastName );
    }

    public String getProfilePicURL() {
        return profilePicURL;
    }

    public void setProfilePicURL(String profilePicURL) {
        this.profilePicURL = profilePicURL;
    }
}
