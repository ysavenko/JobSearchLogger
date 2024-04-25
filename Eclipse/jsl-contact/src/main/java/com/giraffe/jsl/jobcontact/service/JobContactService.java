package com.giraffe.jsl.jobcontact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giraffe.jsl.core.service.AbstractService;
import com.giraffe.jsl.jobcontact.dao.JobContactDao;
import com.giraffe.jsl.jobcontact.dto.Users;
import com.giraffe.jsl.jobcontact.dto.JobContact;

@RestController
@RequestMapping("/jobcontacts")
public class JobContactService extends AbstractService<JobContact> {

	@Autowired
	private JobContactDao jobContactDao;

	@GetMapping("/{user}")
	public List<JobContact> getUserContacts(@PathVariable(name = "user") String candidateName) {
		final Users candidate = new Users();
		candidate.setUsername(candidateName);

		final JobContact example = new JobContact();
		example.setCandidate(candidateName);

		return jobContactDao.findAll(Example.of(example));
	}

	@Override
	protected JpaRepository<JobContact, Long> getDao() {
		return jobContactDao;
	}
}
