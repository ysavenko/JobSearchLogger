package com.giraffe.jsl.jobcontact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giraffe.jsl.core.service.AbstractService;
import com.giraffe.jsl.jobcontact.dao.UsersDao;
import com.giraffe.jsl.jobcontact.dto.Users;

@RestController
@RequestMapping("/users")
public class UserService extends AbstractService<Users>
{

	@Autowired
	private UsersDao usersDao;

	@Override
	protected JpaRepository<Users, Long> getDao()
	{
		return usersDao;
	}
}
