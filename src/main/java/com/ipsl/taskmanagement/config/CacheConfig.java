package com.ipsl.taskmanagement.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collection;

@Configuration(proxyBeanMethods = false)
public class CacheConfig {

    public CacheConfig() {
    }

    @Bean(name = "cacheManager")
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        Collection<Cache> caches = Arrays.asList(
                new ConcurrentMapCache("tasks"),
                new ConcurrentMapCache("pagedTasks")
                // ...
        );
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
