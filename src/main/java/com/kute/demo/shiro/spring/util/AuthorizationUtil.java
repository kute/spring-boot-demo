package com.kute.demo.shiro.spring.util;

import com.google.common.collect.Sets;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Set;

/**
 * created on 2018-03-30 11:41
 */
public class AuthorizationUtil {

    public static final Set<String> EB_ROLES = Sets.newHashSet("eb");
    public static final Set<String> EB_PERMISSIONS = Sets.newHashSet(
            "fruit:apple:eat",
            "fruit:apple:take",
            "fruit:orange,banana:eat"
    );
    public static final Set<String> BD_ROLES = Sets.newHashSet("bd");
    public static final Set<String> BD_PERMISSIONS = Sets.newHashSet(
            "fruit:apple:*",
            "fruit:orange:*"
    );
    public static final Set<String> MTA_ROLES = Sets.newHashSet("mta");
    public static final Set<String> MTA_PERMISSIONS = Sets.newHashSet("fruit:*");

}
