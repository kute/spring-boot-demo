package com.kute.demo.shiro.spring.util;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * created on 2018-04-02 10:39
 */
public class LoadingCacheManager implements CacheManager {

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new LoadingCache<>();
    }
}
