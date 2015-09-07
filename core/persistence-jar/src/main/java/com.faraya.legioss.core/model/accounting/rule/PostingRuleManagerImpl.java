package com.faraya.legioss.core.model.accounting.rule;

import com.faraya.legioss.core.model.accounting.event.AbstractAccountingEvent;
import com.faraya.legioss.core.model.accounting.event.SaleEvent;
import com.faraya.legioss.core.model.accounting.event.SoldItemType;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *
 * Created by fabrizzio on 5/22/15.
 */

@Service
public class PostingRuleManagerImpl implements IPostingRuleManager {

    private Map<SoldItemType,SalePostingRule> saleRulesByType;

    public Map<SoldItemType, SalePostingRule> getSaleRulesByType() {
        return saleRulesByType;
    }

    public void setSaleRulesByType(Map<SoldItemType, SalePostingRule> saleRulesByType) {
        this.saleRulesByType = saleRulesByType;
    }

    public IPostingRule getPostingRule(AbstractAccountingEvent e){
       if(e instanceof SaleEvent){
           SaleEvent saleEvent = SaleEvent.class.cast(e);
           //http://www.accountingtools.com/chart-of-accounts-overview
           // Must be initialized with all required data??
           // like accounts id references coming from the chart of accounts
           SalePostingRule rule = getSaleRulesByType().get(saleEvent.getSoldItemType());
           // some logging here
           return rule;
       }
       throw new RuntimeException("Missing PostingRule Mapping for event of type "+e.getClass().getSimpleName());
    }



}
