package com.ryan.f1tracker;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(java.util.List.of(
            build("standings", Duration.ofMinutes(30)),
            build("races", Duration.ofHours(24)),      
            build("drivers", Duration.ofHours(24)))); 
        return manager;
    }

    private CaffeineCache build(String name, Duration ttl) {
        return new CaffeineCache(name,
            Caffeine.newBuilder()
                .expireAfterWrite(ttl)
                .maximumSize(100) 
                .build());
    }
}
