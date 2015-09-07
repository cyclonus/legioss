package com.faraya.legioss.core.dao.security;

import com.faraya.legioss.core.dao.IGenericDAO;
import com.faraya.legioss.core.entity.security.Credential;

/**
 *
 * Created by fabrizzio on 9/4/15.
 */
public interface ICredentialDAO extends IGenericDAO<Credential,Long> {

    Credential findByOwnerId(Long id);

}
