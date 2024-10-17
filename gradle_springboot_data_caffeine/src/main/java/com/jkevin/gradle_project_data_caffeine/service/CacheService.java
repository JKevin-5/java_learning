package com.jkevin.gradle_project_data_caffeine.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mid2098
 * @version 1.0
 * @description: TODO
 * @date 2024/10/16 15:34
 */
@Service
@Slf4j
public class CacheService {

    // 查询数据
    // 如果没有缓存将刷新缓存
    @Cacheable(value = "cache", key = "#s")
    public String getCache(String s){
        log.info("key: {},没有缓存",s);
        return LocalDateTime.now().toString();
    }

    // 存储数据
    @CachePut(value = "cache", key = "#s")
    public String putCache(String s){
        log.info("key: {},存入缓存",s);
        return LocalDateTime.now().toString();
    }

    // 删除数据
    @CacheEvict(value = "cache", key = "#s")
    public String delCache(String s){
        log.info("key: {},删除缓存",s);
        return LocalDateTime.now().toString();
    }
}
