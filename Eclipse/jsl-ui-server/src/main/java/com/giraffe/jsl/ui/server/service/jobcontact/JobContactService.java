package com.giraffe.jsl.ui.server.service.jobcontact;

import java.util.Collection;

import com.giraffe.jsl.ui.server.dto.DateRange;
import com.giraffe.jsl.ui.server.dto.JobContact;
import com.giraffe.jsl.ui.server.dto.Position;
import com.giraffe.jsl.ui.server.service.Service;

public interface JobContactService extends Service<JobContact>
{

	default Collection<JobContact> getAll(DateRange dateRange) throws Exception
	{
		return getAll(dateRange, JobContact::getDate);
	}

	default JobContact getById(Long id) throws Exception
	{
		return getById(id, JobContact::getId);
	}
	
	default Collection<String> getTypes() throws Exception
	{
		return getAll().stream().map(j -> j.getType()).distinct().toList();
	}

	default Collection<Position> getPositions() throws Exception
	{
		return getAll()
				.stream()
				.map(j -> new Position(j.getPositionId(), j.getPosition(), j.getCompany(), j.getPositionDescription()))
				.distinct()
				.toList();
	}

}
