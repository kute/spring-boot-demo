package com.kute.demo.service.impl;

import com.kute.demo.service.AbstractService;
import com.kute.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by kute on 2017/12/9.
 */
@Service
public class UserService extends AbstractService implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

//    @RequiresRoles(value = {"boy"})
    @Override
    public void updateInventory() {
        LOGGER.info("update inventory:{}", "ok");
    }
}
