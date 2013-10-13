package net.faraya.legioss.core;

import net.faraya.legioss.core.dao.profile.IEducationDAO;
import net.faraya.legioss.core.entity.profile.Education;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

/**
 * User: fabrizzio
 * Date: 10/10/13
 * Time: 10:47 PM
 */
public class UserSimpleTest extends BasePersitenceTest {

    @Autowired
    IEducationDAO educationDAO;

    @Test
    public void andNewUserTest(){


         assertNotNull("null", educationDAO);
         Education education = new Education();
         educationDAO.save(education);
         assertNotNull("null", education.getId());
    }
}
