package com.giraffe.jsl.ui.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Position {

	private Long id;

	private String name;

	private String company;

	private String description;

}
