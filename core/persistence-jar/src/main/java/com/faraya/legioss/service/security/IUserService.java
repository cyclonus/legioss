package com.faraya.legioss.service.security;

import com.faraya.legioss.core.entity.security.IDomain;
import com.faraya.legioss.core.entity.security.IUser;
import com.faraya.legioss.core.entity.security.User;

/**
 * Created by fabrizzio on 9/4/15.
 * https://crackstation.net/hashing-security.htm
 */

public interface IUserService {

    boolean isEmailAlreadyInUse(String email);

    boolean isValidEmail(String email);

    boolean isValidPassword(String password);

    IUser createUser( String email ,String firstName, String lastName, String password, IDomain domain) throws Exception;

    IUser authenticate(String email, String password, IDomain domain) throws Exception;
}
