package com.giraffe.jsl.ui.server.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobContact
{

	private LocalDate date;

	private Long id;

	private String candidate;

	private String type;

	@JsonIgnore
	private String company;

	private Long positionId;

	@JsonIgnore
	private String position;

	private String description;

	private String positionDescription;

}
