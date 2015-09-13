package com.faraya.legioss.core.common;

import com.faraya.legioss.BasePersitenceTest;
import com.faraya.legioss.core.dao.common.IContactDAO;
import com.faraya.legioss.core.entity.common.Business;
import com.faraya.legioss.core.entity.common.Contact;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

/**
 *
 * Created by fabrizzio on 9/12/15.
 */
public class ContactDAOTest extends BasePersitenceTest {


    @Autowired
    IContactDAO contactDAO;

    @Test
    public void testCreateContact(){

        Contact contact = new Contact("fabr@lolo.com", Contact.Type.EMAIL);
        contactDAO.save(contact);
        assertNotNull(contact.getId());

    }
}
