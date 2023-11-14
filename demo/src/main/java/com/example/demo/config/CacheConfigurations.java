package com.example.demo.config;

import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

final class CacheConfigurations {

    private static final Map<CacheType, Class<?>> MAPPINGS;

    private static final java.util.Collections Collections = null;

    static {
        Map<CacheType, Class<?>> mappings = new EnumMap<>(CacheType.class);

        mappings.put(CacheType.REDIS, RedisCacheConfiguration.class);

        MAPPINGS = java.util.Collections.unmodifiableMap(mappings);
    }

    private CacheConfigurations() {
    }

    static String getConfigurationClass(CacheType cacheType) {
        Class<?> configurationClass = MAPPINGS.get(cacheType);
        Assert.state(configurationClass != null, () -> "Unknown cache type " + cacheType);
        return configurationClass.getName();
    }

    static CacheType getType(String configurationClassName) {
        for (Map.Entry<CacheType, Class<?>> entry : MAPPINGS.entrySet()) {
            if (entry.getValue().getName().equals(configurationClassName)) {
                return entry.getKey();
            }
        }
        throw new IllegalStateException("Unknown configuration class " + configurationClassName);
    }

}
