package com.faraya.legioss.core.security;

import com.faraya.legioss.BasePersitenceTest;
import com.faraya.legioss.core.dao.security.ICredentialDAO;
import com.faraya.legioss.core.dao.security.IUserDAO;
import com.faraya.legioss.core.entity.security.Credential;
import com.faraya.legioss.core.entity.security.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * Created by fabrizzio on 9/5/15.
 */
public class UserTestIT extends BasePersitenceTest {

    @Autowired
    IUserDAO userDAO;

    @Autowired
    ICredentialDAO credentialDAO;

    @Test
    public void createUser(){
       User user = new User("fab@gmail.com","fab","ara");
       userDAO.save(user);
       assertNotNull("null id", user.getId());

       User returnedUser = userDAO.findByEmail("fab@gmail.com");
       assertNotNull(returnedUser);
       assertEquals(user.getPrimaryEmail(),returnedUser.getPrimaryEmail());

       Credential credential = new Credential(user,"lol1234");
       credentialDAO.save(credential);
       assertNotNull("null id", credential.getId());
       Credential returnedCredential = credentialDAO.findByOwnerId(user.getId());
       assertEquals(returnedCredential.getPassword(),credential.getPassword());
    }


}
