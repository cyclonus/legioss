package com.faraya.legioss.core.entity.security;

/**
 *
 * Created by fabrizzio on 9/13/15.
 *
 * https://www.tobyhobson.co.uk/spring-security-3-roles-vs-granted-authorities/
 */

public interface IUser {

    enum Status {
        ACTIVE,
        INACTIVE
    }

    Status getStatus();

    String getPrimaryEmail();

    boolean hasRole(String role);

    //boolean hasRole(String role, IDomain domain);


}
