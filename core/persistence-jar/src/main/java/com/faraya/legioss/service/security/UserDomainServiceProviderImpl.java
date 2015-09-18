package com.faraya.legioss.service.security;

import com.faraya.legioss.core.entity.common.Business;
import com.faraya.legioss.core.entity.security.IDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 *
 * Created by fabrizzio on 9/15/15.
 */

@Service
public class UserDomainServiceProviderImpl implements IUserDomainServiceProvider{

    @Autowired
    IUserDomainService userDomainService;

    private Map<Class<? extends IDomain>,IUserDomainService> domainServiceMap = new ConcurrentHashMap<>();

    private Map<Class<? extends IDomain>,IUserDomainService> getDomainServiceMap(){
        if(domainServiceMap.isEmpty()){
           domainServiceMap.putIfAbsent(Business.class,userDomainService);
        }
        return domainServiceMap;
    }

    @Override
    public IUserDomainService getDomainService(Class<IDomain> clazz) {
        return getDomainServiceMap().get(clazz);
    }
}
