package com.kute.demo.orm.mybatis.mapper;

import com.kute.demo.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * created by kute on 2018/09/18 19:55
 */
public interface UserMapper {

    List<User> getAllUser();

    User getById(@Param("id") Integer id);

}
