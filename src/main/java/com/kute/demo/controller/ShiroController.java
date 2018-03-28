package com.kute.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.kute.demo.service.IProductService;
import com.kute.demo.service.IUserService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * created on 2018-03-28 15:33
 */
@Api(value = "ShiroController")
@RestController
@RequestMapping("/shiro")
public class ShiroController {

    private static final Logger logger = LoggerFactory.getLogger(ShiroController.class);

    @Autowired
    private IProductService productService;
    @Autowired
    private IUserService userService;

    @Autowired
    private SecurityManager securityManager;

    @RequestMapping(value = "/index/{user}", method = RequestMethod.GET, produces = "application/json")
    public String index(@PathVariable String user) {

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
            UsernamePasswordToken token = new UsernamePasswordToken(user, "pwd", true);

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
        logger.info("用户【{}】 has role【{}】 result 【{}】", user, "guest", currentUser.hasRole("guest"));

        Map<String , String > responseMap = new HashMap<>(2);

        try {
            productService.createProduct();
        } catch(Exception e) {
            logger.error("role is not allowed:{}, error:{}", user, e.getMessage());
            responseMap.put("code", "-3");
            responseMap.put("message", Joiner.on("").join("用户【", user, "】没有权限【body】"));
            return JSONObject.toJSONString(responseMap);
        }

        try {
            userService.updateInventory();
        } catch(Exception e) {
            logger.error("role is not allowed:{}, error:{}", user, e.getMessage());
            responseMap.put("code", "-1");
            responseMap.put("message", Joiner.on("").join("用户【", user, "】没有权限【body】"));
            return JSONObject.toJSONString(responseMap);
        }

        try {
            otherBussiness();
        } catch(Exception e) {
            logger.error("role is not allowed:{}, error:{}", user, e.getMessage());
            responseMap.put("code", "-2");
            responseMap.put("message", Joiner.on("").join("用户【", user, "】没有权限【girl】"));
            return JSONObject.toJSONString(responseMap);
        }

        responseMap.put("code", "1");
        responseMap.put("message", "Created");
        return JSONObject.toJSONString(responseMap);
    }

//    @RequiresRoles(value = {"girl"})
    private void otherBussiness() {
        logger.info("do other business:{}", "ok");
    }

}
