package com.giraffe.jsl.ui.server.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobContact
{

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String candidate;

	@Getter
	@Setter
	private String type;

	@Getter
	@Setter
	private String description;

	@Getter
	@Setter
	private Long positionId;

	@Getter
	@Setter
	private LocalDate date;

	@JsonIgnore
	@Getter
	@Setter
	private String position;

	@JsonIgnore
	@Getter
	@Setter
	private String company;

	@JsonIgnore
	@Getter
	@Setter
	private String positionDescription;

}
