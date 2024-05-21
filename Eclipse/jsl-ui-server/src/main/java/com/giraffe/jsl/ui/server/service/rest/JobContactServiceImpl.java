package com.giraffe.jsl.ui.server.service.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.giraffe.jsl.ui.server.data.Page;
import com.giraffe.jsl.ui.server.dto.DateRange;
import com.giraffe.jsl.ui.server.dto.JobContact;
import com.giraffe.jsl.ui.server.dto.Position;
import com.giraffe.jsl.ui.server.service.JobContactService;
import com.giraffe.jsl.ui.server.service.PositionService;

//@Service
public class JobContactServiceImpl extends AbstractRestTemplateCrudService<JobContact> implements JobContactService
{

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public PositionService positionService;

	public JobContactServiceImpl(
			@Value("${jobContacts.uri.all}") String getAllUrl,
			@Value("${jobContacts.uri.save}") String saveUrl,
			@Value("${jobContacts.uri.delete}") String deleteUrl)
	{
		super(getAllUrl, saveUrl, deleteUrl);
	}

	@Override
	public Collection<JobContact> getAll() throws Exception
	{
		Map<Long, Position> positions = positionService.getAll().stream()
				.collect(Collectors.toMap(Position::getId, Function.identity()));
		List<JobContact> jobContacts = super.getAll(
				new TypeReference<List<JobContact>>()
				{
				},
				getUsername());
		jobContacts.forEach(jobcontact ->
		{
			Position position = positions.get(jobcontact.getPositionId());
			if (position != null)
			{
				jobcontact.setCompany(position.getCompany());
				jobcontact.setPosition(position.getName());
				// jobcontact.setCompany(position.getCompany());
			}
		});
		return jobContacts;
	}

	private String getUsername()
	{
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@Override
	public Page<JobContact> findPaginated(
			int pageNum,
			int pageSize,
			String sortField,
			String sortDirection,
			DateRange dateRange)
			throws Exception
	{
		List<JobContact> data = new ArrayList<>(getAll(dateRange));
		int totalItems = data.size();
		int totalPages = (totalItems - 1) / pageSize + 1;
		int startIndex = (pageNum - 1) * pageSize;
		int endIndex = Math.min(totalItems, startIndex + pageSize);
		data = data.subList(startIndex, endIndex);
		data = data.stream().sorted(getComparator(sortField, sortDirection)).toList();
		return new Page<JobContact>(data, totalItems, totalPages);
	}

	private static Comparator<JobContact> getComparator(String sortField, String sortDirection)
	{
		Comparator<JobContact> comparator = comparators.get(sortField);
		if ("desc".equals(sortDirection))
		{
			comparator = comparator.reversed();
		}
		return comparator;
	}

	private static final Map<String, Comparator<JobContact>> comparators = new HashMap<>();
	static
	{
		comparators.put("date", Comparator.comparing(JobContact::getDate));
		comparators.put("type", Comparator.comparing(JobContact::getType));
		comparators.put("company", Comparator.comparing(JobContact::getCompany));
		comparators.put("position", Comparator.comparing(JobContact::getPosition));
		comparators.put("description", Comparator.comparing(JobContact::getDescription));
		comparators.put(null, comparators.get("date"));
	}

}
