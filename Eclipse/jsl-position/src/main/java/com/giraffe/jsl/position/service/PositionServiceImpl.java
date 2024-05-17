package com.giraffe.jsl.position.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.giraffe.jsl.core.service.AbstractService;
import com.giraffe.jsl.position.dao.PositionDao;
import com.giraffe.jsl.position.dto.Position;

@RestController
@RequestMapping("/positions")
public class PositionServiceImpl extends AbstractService<Position> implements PositionService
{

	@Autowired
	private PositionDao positionDao;

	@GetMapping("/{company}")
	public Collection<Position> getPositions(@PathVariable(name = "company") String companyName,
			@RequestParam(name = "active", required = false) String activeOnly)
	{
		final Position position = new Position();
		position.setCompany(companyName);

		if (Boolean.parseBoolean(activeOnly))
		{
			position.setActive(true);
		}

		return positionDao.findAll(Example.of(position));
	}

	@GetMapping("/companies")
	public Collection<String> getCompanies()
	{
		return positionDao.getCompanies();
	}

	@Override
	protected JpaRepository<Position, Long> getDao()
	{
		return positionDao;
	}

//	@Cacheable(cacheNames = "positions", key = "#id")
//	public Position get(Long id)
//	{
//		return super.get(id);
//	}
//
////	@CachePut(cacheNames = "positions", key = "#dto.id")
//	public Position add(Position dto)
//	{
//		return super.add(dto);
//	}

}
