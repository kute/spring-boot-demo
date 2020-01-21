package com.kute.jetcachedemo.service.api;

import com.kute.jetcachedemo.dto.UserDto;

/**
 * created by bailong001 on 2019/09/27 16:27
 */
public interface UserService {

    public UserDto getUser(Long id);

    public UserDto getUser_2(Long id);
}
