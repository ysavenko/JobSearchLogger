package com.giraffe.jsl.ui.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import com.giraffe.jsl.ui.server.dto.Users;
import com.giraffe.jsl.ui.server.service.users.UsersService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration
{

	@Autowired
	private UsersService usersService;

	@Bean
	public UserDetailsManager userDetailsService()
	{
		return new UserDetailsManager()
		{
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
			{
				try
				{
					Users user = usersService.getByUsername(username);
					return User.withDefaultPasswordEncoder()
							.username(username)
							.password(user.getPassword())
							.roles("USER")
							.build();
				} catch (Exception e)
				{
					System.out.println(e);
					throw new UsernameNotFoundException("nope", e);
				}
			}

			@Override
			public boolean userExists(String username)
			{
				try
				{
					return null != usersService.getByUsername(username);
				} catch (Exception e)
				{
					return false;
				}
			}

			@Override
			public void updateUser(UserDetails user)
			{
			}

			@Override
			public void deleteUser(String username)
			{
			}

			@Override
			public void createUser(UserDetails user)
			{
			}

			@Override
			public void changePassword(String oldPassword, String newPassword)
			{
			}
		};
	}
}