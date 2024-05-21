package com.giraffe.jsl.jobcontact.dto;

import java.time.LocalDate;

import com.giraffe.jsl.core.dto.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobContact implements Dto
{
	private static final long serialVersionUID = 20240504L;

	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(nullable = false)
	@Getter
	@Setter
	private Long positionId;

	@Column(nullable = false)
	@Getter
	@Setter
	private LocalDate date;

}
