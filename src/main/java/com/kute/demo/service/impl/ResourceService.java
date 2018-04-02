package com.kute.demo.service.impl;

import com.kute.demo.service.AbstractService;
import com.kute.demo.service.IResourceService;
import com.kute.demo.shiro.spring.annotation.OperationAuthorization;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by kute on 2017/12/9.
 */
@Service
public class ResourceService extends AbstractService implements IResourceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceService.class);

    /**
     * 同时需要 bd 和 mta 的角色
     * 注解方式实现
     * @param caller
     */
    @RequiresRoles(value = {"bd", "mta"})
    @Override
    public void openRoom(String caller) {
        LOGGER.info("user【{}】can execute open room[role=bd,mta]", caller);
    }

    /**
     * 需要 bd 的角色
     * 编程方式实现
     * @param caller
     */
    @Override
    public void updatePrice(String caller) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            subject.checkRole("bd");
        }
        LOGGER.info("user【{}】can execute updatePrice[role=bd]", caller);
    }

    /**
     * 需要 food:fruit:apple 权限
     * 切面方式实现
     * @param caller
     */
    @OperationAuthorization
    @Override
    public void queryCommon(String caller) {
        LOGGER.info("user【{}】can execute query common[permission=fruit:apple:eat]", caller);
    }

    @Override
    public void normalThing(String caller) {
        LOGGER.info("user【{}】can execute normalThing without any role or permission", caller);
    }
}
