package com.faraya.legioss.service.accounting.rule;

import com.faraya.legioss.core.dao.accounting.ICatalogDAO;
import com.faraya.legioss.core.dao.accounting.IAccountDAO;
import com.faraya.legioss.core.dao.accounting.IJournalEntryDAO;
import com.faraya.legioss.core.model.accounting.rule.IPostingRule;

/**
 *
 * Created by fabrizzio on 5/25/15.
 */
public abstract class AbstractRuleExecutor implements IRuleExecutor {

    protected IPostingRule rule;

    protected IAccountDAO accountDAO;

    protected ICatalogDAO catalogDAO;

    protected IJournalEntryDAO journalEntryDAO;

    public AbstractRuleExecutor(IPostingRule rule) {
        this.rule = rule;
    }

    public abstract void execute();

}
