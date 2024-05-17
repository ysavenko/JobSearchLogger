package com.giraffe.jsl.ui.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@PropertySource("classpath:/jsl-ui-server.properties")
public class JslUiServerApplication
{
	public static void main(String[] args)
	{

	}
}
