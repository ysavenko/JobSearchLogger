package com.giraffe.jsl.ui.server.service;

import java.time.LocalDate;
import java.util.Collection;

import com.giraffe.jsl.ui.server.dto.DateRange;
import com.giraffe.jsl.ui.server.dto.Position;

public interface PositionService extends Service<Position>
{
	default Collection<Position> getAll(DateRange dateRange) throws Exception
	{
		return getAll(dateRange, p -> LocalDate.now());
	}

	default Position getById(Long id) throws Exception
	{
		return getById(id, Position::getId);
	}

}
