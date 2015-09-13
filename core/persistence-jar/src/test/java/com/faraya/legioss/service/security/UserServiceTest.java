package com.faraya.legioss.service.security;

import com.faraya.legioss.BasePersitenceTest;
import com.faraya.legioss.core.entity.security.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 *
 * Created by fabrizzio on 9/4/15.
 */
public class UserServiceTest extends BasePersitenceTest {

    @Autowired
    IUserService userService;

    @Test
    public void createUserTest()throws Exception{
        User user = userService.createUser("faraya@fabsoft.com", "Fabrizzio", "Araya", "sixCharsMin1Num1$123");
        assertNotNull("null id", user.getId());
        assertEquals(userService.authenticate("faraya@fabsoft.com", "sixCharsMin1Num1$123"),null);
    }

}
