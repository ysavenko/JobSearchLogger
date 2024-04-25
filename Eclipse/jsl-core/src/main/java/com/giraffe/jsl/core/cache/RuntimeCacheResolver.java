package com.giraffe.jsl.core.cache;

import java.util.Collection;
import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;


public class RuntimeCacheResolver extends SimpleCacheResolver
{

	public RuntimeCacheResolver(CacheManager cacheManager)
	{
		super(cacheManager);
	}

	@Override
	protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context)
	{
		return List.of(context.getTarget().getClass().getSimpleName());
	}
}