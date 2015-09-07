package com.faraya.legioss.core.security;

import com.faraya.legioss.BasePersitenceTest;
import com.faraya.legioss.core.dao.security.IPermissionDAO;
import com.faraya.legioss.core.dao.security.IRoleDAO;
import com.faraya.legioss.core.dao.security.IUserDAO;
import com.faraya.legioss.core.entity.security.Permission;
import com.faraya.legioss.core.entity.security.Role;
import com.faraya.legioss.core.entity.security.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 *
 * Created by fabrizzio on 8/5/15.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SecurityGeneralTestIT extends BasePersitenceTest {

    Logger logger = LoggerFactory.getLogger(SecurityGeneralTestIT.class);

    @Autowired
    IUserDAO userDAO;

    @Autowired
    IPermissionDAO permissionDAO;

    @Autowired
    IRoleDAO roleDAO;

    @Test

    public void firstCreateUserAndRoles(){


        Permission execute = new Permission("execute");
        permissionDAO.save(execute);
        assertFalse(execute.isTransient());

        Permission read = new Permission("read");
        permissionDAO.save(read);
        assertFalse(read.isTransient());

        Permission write = new Permission("write");
        permissionDAO.save(write);
        assertFalse(write.isTransient());

        Permission compile = new Permission("compile");
        permissionDAO.save(compile);
        assertFalse(write.isTransient());

        Role admin = new Role("admin");
        admin.addPermission(execute);
        admin.addPermission(read);
        admin.addPermission(write);
        admin.addPermission(compile);
        roleDAO.save(admin);
        assertFalse(admin.isTransient());

        Role manager = new Role("manager");
        roleDAO.save(manager);
        assertFalse(manager.isTransient());

        Role developer = new Role("developer");
        roleDAO.save(developer);
        assertFalse(developer.isTransient());

        User user = new User("devmanager@gmail.com","","");
        user.addRole(manager);
        user.addRole(developer);
        userDAO.save(user);
        assertFalse(user.isTransient());

    }

}
