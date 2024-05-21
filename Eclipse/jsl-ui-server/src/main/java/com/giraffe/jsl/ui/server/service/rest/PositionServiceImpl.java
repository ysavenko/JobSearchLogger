package com.giraffe.jsl.ui.server.service.rest;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.giraffe.jsl.ui.server.dto.Position;
import com.giraffe.jsl.ui.server.service.PositionService;

@Service
public class PositionServiceImpl extends AbstractRestTemplateCrudService<Position> implements PositionService
{

	public PositionServiceImpl(
			@Value("${positions.uri.all}") String getAllUrl,
			@Value("${positions.uri.save}") String saveUrl,
			@Value("${positions.uri.delete}") String deleteUrl)
	{
		super(getAllUrl, saveUrl, deleteUrl);
	}

	@Override
	public Collection<Position> getAll() throws Exception
	{
		return super.getAll(new TypeReference<List<Position>>()
		{
		});
	}

}
