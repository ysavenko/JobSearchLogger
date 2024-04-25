package com.giraffe.jsl.ui.server.data;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Page<T>
{
	private final Collection<T> data;
	private final int totalItems;
	private final int totalPages;
}
