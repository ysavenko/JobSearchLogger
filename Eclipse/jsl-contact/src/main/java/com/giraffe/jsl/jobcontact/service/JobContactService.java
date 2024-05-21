package com.giraffe.jsl.jobcontact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.giraffe.jsl.core.service.AbstractService;
import com.giraffe.jsl.jobcontact.dao.JobContactDao;
import com.giraffe.jsl.jobcontact.dto.JobContact;
import com.giraffe.jsl.jobcontact.dto.Users;

@RestController
@RequestMapping("/jobcontacts")
public class JobContactService extends AbstractService<JobContact>
{

	@Value("${kafka.topic.name}")
	private String topicName;

	@Autowired
	private ObjectMapper objectMapper;

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
		return super.getAll();
	}

	@KafkaListener(topics = "my-topic", groupId = "mygroup")
	public void listen(String message)
	{
		System.out.println("Received Messasge in group - mygroup: " + message);
		try
		{
			super.add(objectMapper.readValue(message, JobContact.class));
		}
		catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SIZE:" + getAll().size());
	}

}
