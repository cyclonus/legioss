package com.faraya.legioss.service.accounting;

import com.faraya.legioss.core.entity.accounting.Business;
import com.faraya.legioss.core.model.accounting.IAccountingEvent;
import com.faraya.legioss.core.model.accounting.ICatalog;

import java.util.List;

/**
 *
 * Created by fabrizzio on 5/11/15.
 */

public interface IAccountingService {

     void post(IAccountingEvent e);

}
