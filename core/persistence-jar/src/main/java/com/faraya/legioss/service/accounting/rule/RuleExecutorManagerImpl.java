package com.faraya.legioss.service.accounting.rule;

import com.faraya.legioss.core.model.accounting.rule.IPostingRule;
import org.springframework.stereotype.Component;

/**
 *
 * Created by fabrizzio on 5/25/15.
 */

@Component
public class RuleExecutorManagerImpl implements IRuleExecutorManager {

    @Override
    public IRuleExecutor getRuleExecutor(IPostingRule rule) {
        return null;
    }
}
