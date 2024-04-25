package com.giraffe.jsl.ui.server.service.users;

import com.giraffe.jsl.ui.server.dto.Users;
import com.giraffe.jsl.ui.server.service.Service;

public interface UsersService extends Service<Users>
{

	default Users getById(Long id) throws Exception
	{
		return getById(id, Users::getId);
	}

	default Users getByUsername(String username) throws Exception
	{
		return getAll().stream().filter(j -> j.getUsername().equals(username)).findFirst().get();
	}

}
