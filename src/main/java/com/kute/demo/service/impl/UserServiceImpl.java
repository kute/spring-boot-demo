package com.kute.demo.service.impl;

import com.kute.demo.po.User;
import com.kute.demo.service.AbstractService;
import com.kute.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * created by kute on 2018/08/12 11:56
 */
@Service("userService")
public class UserServiceImpl extends AbstractService implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Cacheable(key = "#{uid + '_key'}")
    @Override
    public User getUser(int uid) {

        logger.info("getUser by uid:{}", uid);
        return new User(uid);
    }
}
