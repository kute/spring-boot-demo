package com.kute.demo.cache;

import com.google.common.cache.*;
import com.kute.demo.po.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by kute on 2017/12/10.
 */
public class CacheUtil {

    private static final Logger logger = LoggerFactory.getLogger(CacheUtil.class);

    /**
     * 当元素过期被移除时 接受通知,注意此监听器是 在移除时 同步调用的
     */
    private static final RemovalListener<String, UserData> removalListener = new RemovalListener<String, UserData>() {
        @Override
        public void onRemoval(RemovalNotification<String, UserData> removalNotification) {
            logger.info("the data [{}] is being removed and i get it.", removalNotification.getValue());
        }
    };

    /**
     * 本地缓存,降级使用
     */
    private static final LoadingCache<String, UserData> LOCAL_CACHE = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            // 当缓存中项被移除时触发 通知
            .removalListener(removalListener)
            .build(new CacheLoader<String, UserData>() {
                @Override
                public UserData load(String key) throws Exception {
                    return new UserData();
                }
            });

    public static UserData getFromLocalCache(String key) {
        try {
            return LOCAL_CACHE.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
