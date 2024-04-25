package com.giraffe.jsl.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.giraffe.jsl.core.CachingConfiguration;
import com.giraffe.jsl.core.dto.Dto;

@CacheConfig(cacheResolver = CachingConfiguration.CACHE_RESOLVER_NAME)
public abstract class AbstractService<T extends Dto>
{

	@Autowired
	protected abstract JpaRepository<T, Long> getDao();

	@GetMapping("/all")
	@Cacheable("all")
	public List<T> getAll()
	{
		return getDao().findAll();
	}

	@GetMapping("/get/{id}")
	@Cacheable(key = "#id")
	public T get(@PathVariable(value = "id") Long id)
	{
		return getDao().findById(id).get();
	}

	@PostMapping("/save")
	@Caching(
			evict = { @CacheEvict(value = "all", allEntries = true) }, 
			put =   { @CachePut(key = "#dto.id") })
	public T add(@RequestBody T dto)
	{
		return getDao().save(dto);
	}

	@DeleteMapping("/delete/{id}")
	@Caching(
			evict = { @CacheEvict(value = "all", allEntries = true) }, 
			put =   { @CachePut(key = "#id") })
	public void remove(@PathVariable(value = "id") Long id)
	{
		getDao().deleteById(id);
	}

}
