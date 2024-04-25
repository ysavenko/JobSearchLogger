package com.giraffe.jsl.ui;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class JslUiServerConfig
{
	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
}
