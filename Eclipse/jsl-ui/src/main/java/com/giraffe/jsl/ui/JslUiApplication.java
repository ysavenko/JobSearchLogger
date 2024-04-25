package com.giraffe.jsl.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.giraffe.jsl.ui.server.JslUiServerApplication;

//FIXME
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class JslUiApplication extends JslUiServerApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(JslUiApplication.class, args);
	}


}
