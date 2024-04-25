package com.giraffe.jsl.ui.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public abstract class AbstractRestTemplateCrudService<T> implements Service<T>
{
	private final String getAllUrl;
	private final String saveUrl;
	private final String deleteUrl;

	private final ObjectMapper objectMapper;

	@Autowired
	private RestTemplate restTemplate;

	public AbstractRestTemplateCrudService(
			String getAllUrl,
			String saveUrl,
			String deleteUrl)
	{
		this.getAllUrl = getAllUrl;
		this.saveUrl = saveUrl;
		this.deleteUrl = deleteUrl;

		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
	}

	public <K> K getAll(
			TypeReference<K> typeReference,
			Object... uriVariables) throws Exception
	{
		return objectMapper.readValue(
				restTemplate.getForObject(getAllUrl, String.class, uriVariables),
				typeReference);
	}

	public void save(T entity, Object... uriVariables) throws Exception
	{
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		final HttpEntity<T> httpEntity = new HttpEntity<>(entity, headers);

		restTemplate.postForEntity(saveUrl, httpEntity, String.class);
	}

	public void save(T entity) throws Exception
	{
		save(entity, new Object[0]);
	}

	public void delete(long id, Object... uriVariables)
	{
		restTemplate.delete(deleteUrl, id);
	}

	public void delete(long id)
	{
		delete(id, new Object[0]);
	}

}
