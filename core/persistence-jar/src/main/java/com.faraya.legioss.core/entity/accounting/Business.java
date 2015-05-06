package com.faraya.legioss.core.entity.accounting;

import com.faraya.legioss.core.entity.common.Address;
import com.faraya.legioss.core.entity.common.Period;

import javax.persistence.Embedded;
import java.util.Set;

/**
 * Created by fabrizzio on 4/24/15.
 * Company / Business
 */

public class Business {

    private Long id;

    private String name;

    private Currency defaultCurrency;

    private Set<Address> address;

    @Embedded
    private Period businessYear;

}
