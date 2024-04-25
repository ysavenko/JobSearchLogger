package com.giraffe.jsl.ui.server.service.users;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.giraffe.jsl.ui.server.dto.Users;
import com.giraffe.jsl.ui.server.service.AbstractRestTemplateCrudService;

@Service
public class UserServiceImpl extends AbstractRestTemplateCrudService<Users> implements UsersService
{

	public UserServiceImpl(@Value("${users.uri.all}") String getAllUrl)
	{
		super(getAllUrl, null, null);
		System.out.println(" >> USERS URL: "+getAllUrl);		
	}

	@Override
	public Collection<Users> getAll() throws Exception
	{
		return super.getAll(new TypeReference<List<Users>>()
		{
		});
	}

}
