package com.faraya.legioss.service.security;

import com.faraya.legioss.core.entity.security.IDomain;

/**
 *
 * Created by fabrizzio on 9/15/15.
 */
public interface IUserDomainServiceProvider {

    IUserDomainService getDomainService(Class<IDomain> clazz);

}
