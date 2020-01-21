package com.kute.jetcachedemo.service;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.anno.CreateCache;
import com.kute.jetcachedemo.dto.UserDto;
import com.kute.jetcachedemo.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * created by bailong001 on 2019/09/26 15:47
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    /**
     * 创建cache对象
     */
    @CreateCache(name = "UserService.userCache",
            expire = 10,
            timeUnit = TimeUnit.SECONDS,
            cacheType = CacheType.LOCAL)
    private Cache<Long, UserDto> userCache;

    /**
     * 注解添加到方法上
     *
     * @param id
     * @return
     */
    @Cached(name = "UserService.getUser", expire = 3600)
    @Override
    public UserDto getUser(Long id) {
        log.info("getUser param id={}", id);
        return getFromDB(id);
    }

    @Override
    public UserDto getUser_2(Long id) {
        log.info("getUser_2 param id={}", id);
        UserDto userDto = userCache.get(id);
        if (null != userDto) {
            log.info("getUser_2 from cache for id={}", id);
            return userDto;
        }
        userDto = getFromDB(id);
        userCache.put(id, userDto);
        return userDto;
    }

    public UserDto getFromDB(Long id) {
        return new UserDto()
                .setId(id)
                .setName(RandomStringUtils.randomAlphanumeric(5))
                .setMoney(RandomUtils.nextDouble(100D, 200D));
    }


}
