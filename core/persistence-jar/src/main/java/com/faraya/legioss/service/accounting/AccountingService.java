package com.faraya.legioss.service.accounting;

import com.faraya.legioss.core.model.accounting.event.AbstractAccountingEvent;
import com.faraya.legioss.core.model.accounting.rule.IPostingRule;
import com.faraya.legioss.core.model.accounting.rule.IPostingRuleManager;
import com.faraya.legioss.service.accounting.rule.IRuleExecutor;
import com.faraya.legioss.service.accounting.rule.IRuleExecutorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 *
 * Created by fabrizzio on 5/11/15.
 */

@Service
public class AccountingService implements IAccountingService{

    @Autowired
    IPostingRuleManager postingRuleManager;

    @Autowired
    IRuleExecutorManager ruleExecutorManager;

    @Override
    public void process(AbstractAccountingEvent e) {

        IPostingRule rule = postingRuleManager.getPostingRule(e);
        IRuleExecutor ruleExecutor = ruleExecutorManager.getRuleExecutor(rule);
        ruleExecutor.execute();
    }



    /**
     * Must return always 0, otherwise balance is broken
     * @return
     * @throws Exception
     */
    protected BigDecimal getTrialBalance(Long businessId) throws Exception{

        return new BigDecimal(0);
    }


}
