package com.faraya.legioss.service.security;

import com.faraya.legioss.core.entity.security.User;

/**
 * Created by fabrizzio on 9/4/15.
 * https://crackstation.net/hashing-security.htm
 */

public interface IUserService {

    boolean isEmailAlreadyInUse(String email);

    boolean isValidEmail(String email);

    boolean isValidPassword(String password);

    User createUser( String email ,String firstName, String lastName, String password) throws Exception;

    User authenticate(String email, String password) throws Exception;
}
