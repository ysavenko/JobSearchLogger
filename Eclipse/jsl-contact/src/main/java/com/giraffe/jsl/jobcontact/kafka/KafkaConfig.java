package com.giraffe.jsl.jobcontact.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;

@EnableKafka
@Configuration
public class KafkaConfig
{
	@Value("${kafka.bootstrap.servers}")
	private String kafkaBootstrapServers;

	@Value("${kafka.topic.name}")
	private String topicName;

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory()
	{
		System.out.println("KAFKA CONSUMER:" + kafkaBootstrapServers);
		return com.giraffe.jsl.core.kafka.KafkaConfig.kafkaListenerContainerFactory(kafkaBootstrapServers, topicName);
	}

	//@Bean
	public KafkaTemplate<String, String> kafkaTemplate()
	{
		return com.giraffe.jsl.core.kafka.KafkaConfig.createKafkaTemplate(kafkaBootstrapServers);
	}
}
