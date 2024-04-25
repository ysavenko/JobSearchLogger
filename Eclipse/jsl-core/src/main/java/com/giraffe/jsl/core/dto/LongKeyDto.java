package com.giraffe.jsl.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public interface LongKeyDto extends Dto
{
	Long getId();
}
