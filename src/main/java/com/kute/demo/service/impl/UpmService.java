package com.kute.demo.service.impl;

import com.kute.demo.service.AbstractService;
import com.kute.demo.service.IUpmService;
import com.kute.demo.shiro.spring.util.AuthorizationEnum;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by kute on 2017/12/9.
 */
@Service
public class UpmService extends AbstractService implements IUpmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmService.class);

    @Override
    public Set<String> getRoles(String principal) {
        return getAuthorizationInfo(principal).getRoles();
    }

    @Override
    public Set<String> getPermissions(String principal) {
        return getAuthorizationInfo(principal).getStringPermissions();
    }

    @Override
    public SimpleAuthorizationInfo getAuthorizationInfo(String principal) {
        try {
            return AuthorizationEnum.valueOf(principal.trim().toUpperCase()).initAuthorization();
        } catch(IllegalArgumentException e) {
            LOGGER.warn("User[{}] have not been defined any roles or permission.", principal);
        }
        return defaultSimpleAuthorizationInfo(principal);
    }
}
