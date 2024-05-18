package com.giraffe.jsl.jobcontact.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import com.giraffe.jsl.core.kafka.KafkaProducerConfig;

@Configuration
public class KafkaConfig
{
	@Value("${kafka.bootstrap.servers}")
	private String kafkaBootstrapServers;

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate()
	{
		return KafkaProducerConfig.createKafkaTemplate(kafkaBootstrapServers);
	}
}
