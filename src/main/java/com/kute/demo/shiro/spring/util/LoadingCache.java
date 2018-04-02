package com.kute.demo.shiro.spring.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.primitives.Ints;
import com.kute.demo.service.IUpmService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 * cache for shiro cache manager
 * created on 2018-04-02 10:40
 */
public class LoadingCache<K, V> implements Cache<K, V> {

    private static final Logger logger = LoggerFactory.getLogger(LoadingCache.class);

    private final RemovalListener<K, V> removalListener = removalNotification -> {
        logger.info("[key={}, value={}] is removed from cache", removalNotification.getKey(), removalNotification.getValue());
    };

    private final com.google.common.cache.Cache<K, V> CACHE = CacheBuilder.newBuilder()
            .maximumSize(500)
            // 5m 内未再创建或者替换 自动失效移除
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .removalListener(removalListener)
            .recordStats()
            .build();

    public LoadingCache() {
        super();
    }

    @Override
    public V get(K key) throws CacheException {
        V value = CACHE.getIfPresent(key);
        logger.info("LoadingCache get key:{}, value:{}", key, value);
        return value;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        logger.info("LoadingCache put key:{}, value:{}", key, value);
        CACHE.put(key, value);
        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
        V value = get(key);
        logger.info("LoadingCache remove key:{}, value:{}", key, value);
        CACHE.invalidate(key);
        return value;
    }

    @Override
    public void clear() throws CacheException {
        CACHE.cleanUp();
    }

    @Override
    public int size() {
        return Ints.checkedCast(CACHE.size());
    }

    @Override
    public Set<K> keys() {
        return CACHE.asMap().keySet();
    }

    @Override
    public Collection<V> values() {
        return CACHE.asMap().values();
    }
}
