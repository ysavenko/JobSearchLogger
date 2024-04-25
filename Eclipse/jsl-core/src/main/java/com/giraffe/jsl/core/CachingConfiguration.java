package com.giraffe.jsl.core;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giraffe.jsl.core.cache.RuntimeCacheResolver;

@Configuration
@EnableCaching
public class CachingConfiguration implements CachingConfigurer
{

	public final static String CACHE_RESOLVER_NAME = "simpleCacheResolver";

	@Bean
	@Override
	public CacheManager cacheManager()
	{
		return new ConcurrentMapCacheManager();
	}

	@Bean(CACHE_RESOLVER_NAME)
	public CacheResolver cacheResolver(CacheManager cacheManager)
	{
		return new RuntimeCacheResolver(cacheManager);
	}
}