package com.faraya.legioss.core.model.accounting.rule;

import com.faraya.legioss.core.model.accounting.event.AbstractAccountingEvent;

/**
 * Created by fabrizzio on 5/22/15.
 */
public interface IPostingRuleManager {

    IPostingRule getPostingRule(AbstractAccountingEvent event);

}
