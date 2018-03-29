package com.kute.demo.service.impl;

import com.kute.demo.service.AbstractService;
import com.kute.demo.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by kute on 2017/12/9.
 */
@Service
public class UserService extends AbstractService implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @RequiresRoles(value = {"bd"})
    @Override
    public void openRoom(String caller) {
        LOGGER.info("【{}】open room", caller);
    }

    @RequiresRoles(value = {"bd"})
    @Override
    public void updatePrice(String caller) {
        LOGGER.info("【{}】updatePrice", caller);
    }

    @RequiresPermissions(value = {"userService:resource:queryCommon"})
    @Override
    public void queryCommon(String caller) {
        LOGGER.info("【{}】 can do query common", caller);
    }
}
