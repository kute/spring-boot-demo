package com.kute.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.kute.demo.service.IUserService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IUserService userService;

    @Autowired
    private SecurityManager securityManager;

    @RequestMapping(value = "/index/{user}/{operation}", method = RequestMethod.GET, produces = "application/json")
    public String index(
            @PathVariable String user,
            @PathVariable String operation) {

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

        if("price".equals(operation)) {
            doPriceTask(user);
        } else if("room".equals(operation)) {
            doRoom(user);
        }

        otherBussiness(user);

        Map<String , String > responseMap = new HashMap<>(2);
        responseMap.put("code", "1");
        responseMap.put("message", "Created");
        return JSONObject.toJSONString(responseMap);
    }

    private void doRoom(String caller) {
        try {
            userService.openRoom(caller);
        } catch(Exception e) {
            logger.error("role is not allowed for openRoom:{}, error:{}", caller, e.getMessage());
        }
    }

    private void doPriceTask(String caller) {
        try {
            userService.updatePrice(caller);
        } catch(Exception e) {
            logger.error("role is not allowed for updatePrice:{}, error:{}", caller, e.getMessage());
        }
    }

    private void otherBussiness(String caller) {
        try {
            userService.queryCommon(caller);
        } catch(Exception e) {
            logger.error("role【{}】 is not allowed for queryCommon, error:{}", caller, e.getMessage());
        }
    }

}
