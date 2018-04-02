package com.kute.demo.shiro.spring.realm;

import com.google.common.base.Strings;
import com.kute.demo.service.IResourceService;
import com.kute.demo.service.IUpmService;
import com.kute.demo.shiro.spring.util.AuthenticationUtil;
import com.kute.demo.shiro.spring.util.AuthorizationEnum;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 数据源
 * created by kute on 2018-03-28 12:56
 */
public class CustomRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRealm.class);

    private IUpmService upmService;

    public void setUpmService(IUpmService upmService) {
        this.upmService = upmService;
    }

    /**
     * 执行 授权
     *
     * 模拟 获取 用户 角色权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String user = (String) principals.getPrimaryPrincipal();
        LOGGER.info("doGetAuthorizationInfo with user[{}]", user);
        if(AuthorizationEnum.contains(user)) {
            SimpleAuthorizationInfo authorizationInfo = upmService.getAuthorizationInfo(user);
            LOGGER.info("user[{}] has roles[{}] and permissions[{}]", user, authorizationInfo.getRoles(), authorizationInfo.getStringPermissions());
            return authorizationInfo;
        }
        return null;
    }

    /**
     * 执行 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String user = (String) token.getPrincipal();
        if(Strings.isNullOrEmpty(user)) {
            return null;
        }
        LOGGER.info("doGetAuthenticationInfo with user[{}]", user);
        return new SimpleAuthenticationInfo(user, AuthenticationUtil.DEFAULT_PASSWORD, user);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return super.supports(token);
    }
}

