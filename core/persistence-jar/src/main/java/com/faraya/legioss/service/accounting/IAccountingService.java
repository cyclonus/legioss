package com.faraya.legioss.service.accounting;

import com.faraya.legioss.core.model.accounting.event.AbstractAccountingEvent;

/**
 *
 * Created by fabrizzio on 5/11/15.
 */

public interface IAccountingService {

     void process(AbstractAccountingEvent e);



}
