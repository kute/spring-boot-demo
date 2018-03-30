package com.kute.demo.service;

import com.google.common.collect.Sets;
import org.apache.shiro.authz.SimpleAuthorizationInfo;

import java.util.Set;

/**
 * Created by kute on 2017/12/9.
 */
public interface IUpmService {

    default SimpleAuthorizationInfo defaultSimpleAuthorizationInfo(String principal) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(Sets.newHashSet(principal));
        simpleAuthorizationInfo.addStringPermission("*");
        return simpleAuthorizationInfo;
    }

    Set<String > getRoles(String principal);

    Set<String > getPermissions(String principal);

    /**
     * 获取用户 授权，若无 则 返回默认授权
     * @param principal
     * @return
     */
    SimpleAuthorizationInfo getAuthorizationInfo(String principal);

}
