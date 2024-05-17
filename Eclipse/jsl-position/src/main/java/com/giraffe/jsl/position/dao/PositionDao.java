package com.giraffe.jsl.position.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.giraffe.jsl.position.dto.Position;

@Repository
public interface PositionDao extends JpaRepository<Position, Long> {

	  @Query(value = "SELECT DISTINCT company FROM position", nativeQuery = true)
	  Collection<String> getCompanies();
}
