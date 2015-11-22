package com.faraya.legioss.core.security;

import com.faraya.legioss.TransactionalSpringJUnit4RunnerTest;
import com.faraya.legioss.core.dao.common.*;
import com.faraya.legioss.core.dao.security.ICredentialDAO;
import com.faraya.legioss.core.dao.security.IPermissionDAO;
import com.faraya.legioss.core.dao.security.IRoleDAO;
import com.faraya.legioss.core.dao.security.IUserDAO;
import com.faraya.legioss.core.entity.common.*;
import com.faraya.legioss.core.entity.security.Credential;
import com.faraya.legioss.core.entity.security.Permission;
import com.faraya.legioss.core.entity.security.Role;
import com.faraya.legioss.core.entity.security.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 *
 * Created by fabrizzio on 9/5/15.
 */
public class UserTestIT extends TransactionalSpringJUnit4RunnerTest {

    @Autowired
    IUserDAO userDAO;

    @Autowired
    ICredentialDAO credentialDAO;

    @Autowired
    IBusinessDAO businessDAO;

    @Autowired
    IUserBusinessDomainDAO userBusinessDomainDAO;

    @Autowired
    IPermissionDAO permissionDAO;

    @Autowired
    IRoleDAO roleDAO;

    @Test
    @Rollback(true)
    public void createUserBusinessAndDomain() {

        BasicCurrency currency = new BasicCurrency("USD");

        Address address = new Address("CR","la perica","Palmares","Alajuela","-");

        Contact contact = new Contact("fabaraya@hotmail.com", Contact.Type.EMAIL);

        Business business = new Business("Legioss Software S.A.", currency, new Period(LocalDate.now().plusYears(1)));
        business.addAddress(address);
        business.addContact(contact);

        businessDAO.save(business);
        assertNotNull("null id", business.getId());

        User user = new User("fab@gmail.com", "fab", "ara");
        userDAO.save(user);
        assertNotNull("null id", user.getId());

        User returnedUser = userDAO.findByEmail("fab@gmail.com");
        assertNotNull(returnedUser);
        assertEquals(user.getPrimaryEmail(), returnedUser.getPrimaryEmail());

        Credential credential = new Credential(user, "lol1234");
        credentialDAO.save(credential);
        assertNotNull("null id", credential.getId());
        Credential returnedCredential = credentialDAO.findByOwnerId(user.getId());
        assertEquals(returnedCredential.getPassword(), credential.getPassword());

        UserBusinessDomain userBusinessDomain = new UserBusinessDomain(user, business);
        userBusinessDomainDAO.save(userBusinessDomain);
        assertNotNull(userBusinessDomain.getId());

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

        userBusinessDomain.addRole(admin);
        userBusinessDomain.addRole(manager);
        userBusinessDomain.addRole(developer);

        userBusinessDomainDAO.save(userBusinessDomain);

        UserBusinessDomain fetchedUserBusinessDomain = userBusinessDomainDAO.findByPK(userBusinessDomain.getId());
        assertEquals("roles weren't there", 3, fetchedUserBusinessDomain.getRoles().size());

    }


}
