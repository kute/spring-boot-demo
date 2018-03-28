package com.kute.demo.shiro.quickstart;

import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.util.Factory;

/**
 * created by kute on 2018/03/27 22:12
 */
public class QuickStart {

    private static final Logger logger = LoggerFactory.getLogger(QuickStart.class);

    public static void main(String[] args) {

        // shiro 环境初始化
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/quickstart/shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);


        // 创建 一个 匿名 用户
        Subject currentUser = SecurityUtils.getSubject();

        // 设置一些属性
        Session session = currentUser.getSession();
        session.setAttribute("key", "value");

        if(!currentUser.isAuthenticated()) {
            // 用户认证
            UsernamePasswordToken token = new UsernamePasswordToken("kute", "pwd", true);

            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                logger.info("用户不存在：{}", token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                logger.info("密码不正确:{}", new String(token.getPassword()));
            } catch (LockedAccountException lae) {
                logger.info("用户被锁定：{}", token.getPrincipal());
            }
            catch (AuthenticationException ae) {
                logger.error("认证其他错误", ae);
            }
        }

        logger.info("用户【{}】 认证成功", currentUser.getPrincipal());

        //判断用户 角色
        currentUser.isAuthenticated();
        currentUser.hasRole("guest");
        currentUser.hasRoles(Lists.newArrayList("admin", "developer"));
        currentUser.hasAllRoles(Lists.newArrayList("admin", "developer"));

        // 若 无角色，抛异常 UnauthorizedException
        currentUser.checkRole("admin");
        logger.info("{}", currentUser.hasRole("白名单"));

        //判断 权限
        currentUser.isPermitted("aa");
        currentUser.isPermitted("userService:create", "userService:update");
        currentUser.isPermittedAll("userService:create", "userService:update");

        currentUser.checkPermission("userService:create");


        // logout
        currentUser.logout();
    }
}
