package com.giraffe.jsl.jobcontact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giraffe.jsl.core.service.AbstractService;
import com.giraffe.jsl.jobcontact.dao.JobContactDao;
import com.giraffe.jsl.jobcontact.dto.JobContact;
import com.giraffe.jsl.jobcontact.dto.Users;

@RestController
@RequestMapping("/jobcontacts")
public class JobContactService extends AbstractService<JobContact>
{
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${kafka.topic.name}")
	private String topicName;

	@Autowired
	private JobContactDao jobContactDao;

	@GetMapping("/{user}")
	public List<JobContact> getUserContacts(@PathVariable(name = "user") String candidateName)
	{
		final Users candidate = new Users();
		candidate.setUsername(candidateName);

		final JobContact example = new JobContact();
		example.setCandidate(candidateName);

		return jobContactDao.findAll(Example.of(example));
	}

	@Override
	protected JpaRepository<JobContact, Long> getDao()
	{
		return jobContactDao;
	}

	@Override
	public List<JobContact> getAll()
	{
		send("Requested all CONTACTS");
		return super.getAll();
	}

	@Override
	public JobContact add(JobContact dto)
	{
		final JobContact jobContact = super.add(dto);
		send("JOB CONTACT ADDED " + jobContact.getId());
		return jobContact;

	}

	private void send(String message)
	{
		try
		{
			kafkaTemplate.send(topicName, message);
		} catch (Exception e)
		{
			System.out.println("FAILED TO SEND KAFKA MESSAGE: " + e);
		} finally
		{
		}
	}

}
