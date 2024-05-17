package com.giraffe.jsl.ui.server.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.function.Function;

import com.giraffe.jsl.ui.server.data.Page;
import com.giraffe.jsl.ui.server.dto.DateRange;

public interface Service<T>
{

	Collection<T> getAll() throws Exception;

	void save(T course) throws Exception;

	void delete(long id);

	T getById(Long id) throws Exception;

	default Page<T> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection,
			DateRange dateRange)
			throws Exception
	{
		Collection<T> data = getAll();
		return new Page<T>(data, data.size(), 1);
	}

	default T getById(Long id, Function<T, Long> idSupplier) throws Exception
	{
		return getAll().stream().filter(j -> idSupplier.apply(j).equals(id)).findFirst().get();
	}

	default Collection<T> getAll(DateRange dateRange, Function<T, LocalDate> dateSupplier) throws Exception
	{
		return getAll().stream().filter(element ->
		{
			LocalDate date = dateSupplier.apply(element);
			if (dateRange.getFrom() != null && date.isBefore(dateRange.getFrom()))
			{
				return false;
			}
			if (dateRange.getTo() != null && date.isAfter(dateRange.getTo()))
			{
				return false;
			}
			return true;
		}).toList();
	}
}
