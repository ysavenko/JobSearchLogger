package com.giraffe.jsl.ui.server.service.kafka;

import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.giraffe.jsl.ui.server.service.Service;

public abstract class AbstractKafkaTemplateCrudService<T> implements Service<T>
{
	private final String getAllUrl;
	private final String deleteUrl;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	private RestTemplate restTemplate;

	public AbstractKafkaTemplateCrudService(
			String getAllUrl,
			String deleteUrl)
	{
		this.getAllUrl = getAllUrl;
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

	public void save(T entity) throws Exception
	{
		try
		{
			StringWriter w = new StringWriter();
			objectMapper.writeValue(w, entity);
			kafkaTemplate.send("my-topic", w.toString());
		}
		catch (Exception e)
		{
			System.out.println("FAILED TO SEND KAFKA MESSAGE: " + e);
		}
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
