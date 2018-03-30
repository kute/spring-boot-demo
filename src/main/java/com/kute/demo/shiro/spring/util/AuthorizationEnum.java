package com.kute.demo.shiro.spring.util;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.SimpleAuthorizationInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum AuthorizationEnum {

    EB("EB") {
        @Override
        public SimpleAuthorizationInfo initAuthorization() {
            return AuthorizationEnum.getSimpleAuthorizationInfo(AuthorizationUtil.EB_ROLES, AuthorizationUtil.EB_PERMISSIONS);
        }
    },

    BD("BD") {
        @Override
        public SimpleAuthorizationInfo initAuthorization() {
            return AuthorizationEnum.getSimpleAuthorizationInfo(AuthorizationUtil.BD_ROLES, AuthorizationUtil.BD_PERMISSIONS);
        }
    },

    MTA("MTA") {
        @Override
        public SimpleAuthorizationInfo initAuthorization() {
            return AuthorizationEnum.getSimpleAuthorizationInfo(AuthorizationUtil.MTA_ROLES, AuthorizationUtil.MTA_PERMISSIONS);
        }
    };

    private static Map<String, AuthorizationEnum> map = new HashMap<>(3);

    public static Boolean contains(String mark) {
        if(MapUtils.isEmpty(map)) {
            synchronized (AuthorizationEnum.class) {
                if(MapUtils.isEmpty(map)) {
                    map = Maps.uniqueIndex(Lists.newArrayList(AuthorizationEnum.values()), authorizationEnum -> authorizationEnum.getMark());
                }
            }
        }
        return !Strings.isNullOrEmpty(mark) && map.containsKey(mark.trim().toUpperCase());
    }

    private String mark;

    AuthorizationEnum(final String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    private static SimpleAuthorizationInfo getSimpleAuthorizationInfo(Set<String> roles, Set<String > permissions) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    public abstract SimpleAuthorizationInfo initAuthorization();
}
