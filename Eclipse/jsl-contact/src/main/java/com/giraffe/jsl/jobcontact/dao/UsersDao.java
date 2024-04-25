package com.giraffe.jsl.jobcontact.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giraffe.jsl.jobcontact.dto.Users;

@Repository
public interface UsersDao extends JpaRepository<Users, Long> {

}
