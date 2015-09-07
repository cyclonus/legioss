package com.faraya.legioss.core.dao.security;

import com.faraya.legioss.core.dao.IGenericDAO;
import com.faraya.legioss.core.entity.security.User;

/**
 * Created by fabrizzio on 8/3/15.
 */

public interface IUserDAO extends IGenericDAO<User,Long> {

    User findByEmail(String email);

}
