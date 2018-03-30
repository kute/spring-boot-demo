package com.kute.demo.shiro.spring.realm;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;

import java.util.Collection;

/**
 * created on 2018-03-30 15:12
 */
public class CustomRolePermissionResolver implements RolePermissionResolver {
    @Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        return null;
    }
}
