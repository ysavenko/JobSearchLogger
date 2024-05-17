package com.giraffe.jsl.ui.server.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DateRange
{

	private final LocalDate from;
	private final LocalDate to;

	public String getUrl()
	{
		final StringBuilder url = new StringBuilder();
		url.append("from=");
		if (getFrom() != null)
		{
			url.append(getFrom());
		}
		url.append("&to=");
		if (getTo() != null)
		{
			url.append(getTo());
		}
		return url.toString();
	}
}
