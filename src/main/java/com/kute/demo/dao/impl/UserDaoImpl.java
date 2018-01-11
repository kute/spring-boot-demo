package com.kute.demo.dao.impl;

import com.kute.demo.dao.IUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by kute on 2018/1/11.
 */
@Service
public class UserDaoImpl implements IUserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    /**
     * use masterdb
     */
    @Autowired
    @Qualifier("masterJdbcTemplate")
    private JdbcTemplate masterJdbcTemplate;

    /**
     * use slavedb
     */
    @Autowired
    @Qualifier("slaveJdbcTemplate")
    private JdbcTemplate slaveJdbcTemplate;

    /**
     * use masterdb same as masterJdbcTemplate
      */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void execute(String sql) {
        logger.info("User dao execute sql:{}", sql);
        jdbcTemplate.execute(sql);
    }
}
