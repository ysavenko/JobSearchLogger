package com.giraffe.jsl.jobcontact.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giraffe.jsl.jobcontact.dto.JobContact;

@Repository
public interface JobContactDao extends JpaRepository<JobContact, Long> {

}
