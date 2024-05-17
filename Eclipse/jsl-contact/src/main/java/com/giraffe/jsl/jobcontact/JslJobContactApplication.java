package com.giraffe.jsl.jobcontact;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.giraffe.jsl.core.JslCoreApplication;
import com.giraffe.jsl.jobcontact.dto.JobContact;
import com.giraffe.jsl.jobcontact.dto.Users;
import com.giraffe.jsl.jobcontact.service.JobContactService;
import com.giraffe.jsl.jobcontact.service.UserService;

@SpringBootApplication
public class JslJobContactApplication extends JslCoreApplication
{

	public static void main(String[] args)
	{
		ConfigurableApplicationContext run = SpringApplication.run(JslJobContactApplication.class, args);
		jobContactService = run.getBean(JobContactService.class);
		userService = run.getBean(UserService.class);

		addUsers("Ievgen", "John", "Who");

		add("Apply", "Sent resume", 7);
		add("Phone", "Employment status clarification with HR. 603 603-6036", 7);
		add("Email", "Interview Scheduled by email", 6);
		add("Email", "Interview Scheduled by email", 6);
		for (int i = 1; i < 4; i++)
		{
			add("Video", "Interview Roud #" + i, 6 - i);
		}
		add("Email", "Got an offer", 1);
		add("Email", "Offer accepted", 0);

		System.out.println(jobContactService.getAll().size());

	}

	private static JobContactService jobContactService;
	private static UserService userService;

	private static void add(String type, String description, int daysAgo)
	{
		try
		{
			jobContactService
					.add(new JobContact(null, "John", type, description, 3L, LocalDate.now().minusDays(daysAgo)));
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}

	private static void addUsers(String... names)
	{
		try
		{
			for (String name : names)
			{
				userService.add(new Users(null, name, "123", 'Y'));
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
