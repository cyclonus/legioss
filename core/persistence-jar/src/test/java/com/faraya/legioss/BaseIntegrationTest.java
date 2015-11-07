package com.faraya.legioss;

import com.faraya.legioss.core.dao.common.IBusinessDAO;
import com.faraya.legioss.core.dao.common.ICurrencyDAO;
import com.faraya.legioss.core.entity.common.*;
import com.faraya.legioss.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

/**
 *
 * Created by fabrizzio on 11/6/15.
 */

public abstract class BaseIntegrationTest extends TransactionalSpringJUnit4RunnerTest {

    @Autowired
    IBusinessDAO businessDAO;

    @Autowired
    ICurrencyDAO currencyDAO;

    public Business createBusiness(){
        Currency currency = new Currency("US Dollar","USD","$");
        currencyDAO.save(currency);
        assertNotNull("null id", currency.getId());
        Address address = new Address("CR","la perica","Palmares","Alajuela","-");
        Contact contact = new Contact("fabaraya@hotmail.com", Contact.Type.EMAIL);
        Business business = new Business("Legioss Software S.A.", currency, new Period(DateUtils.computeYearsFrom(new Date(), 1)));
        business.addAddress(address);
        business.addContact(contact);
        return businessDAO.save(business);
    }

}
