package com.giraffe.jsl.position.dto;

import java.time.LocalDate;

import com.giraffe.jsl.core.dto.LongKeyDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Position implements LongKeyDto
{

	private static final long serialVersionUID = 20240503;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String internalId;

	@Getter
	@Setter
	private String name;

	@Column(nullable = false)
	@Getter
	@Setter
	private String company;

	@Getter
	@Setter
	private String description;

	@Getter
	@Setter
	private LocalDate posted;

	@Getter
	@Setter
	private LocalDate archived;

	@Getter
	@Setter
	private Boolean active;

}
