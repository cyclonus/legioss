package com.faraya.legioss;

import com.faraya.legioss.core.dao.common.IBusinessDAO;
import com.faraya.legioss.core.entity.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

/**
 *
 * Created by fabrizzio on 11/6/15.
 */

public abstract class BaseIntegrationTest extends TransactionalSpringJUnit4RunnerTest {

    @Autowired
    IBusinessDAO businessDAO;


    public Business createBusiness(){
        BasicCurrency currency = new BasicCurrency("USD");
        Address address = new Address("CR","la perica","Palmares","Alajuela","-");
        Contact contact = new Contact("fabaraya@hotmail.com", Contact.Type.EMAIL);
        Business business = new Business("Legioss Software S.A.", currency, new Period(LocalDate.now().plusYears(1)));
        business.addAddress(address);
        business.addContact(contact);
        return businessDAO.save(business);
    }

}
