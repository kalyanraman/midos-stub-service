package com.eon.applypayment.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.eon.applypayment.vo.ApplyPaymentStatusEvents;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	public DefaultKafkaConsumerFactory<String, ApplyPaymentStatusEvents> midosStubtConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id");
		props.put("value.deserializer", "com.eon.applypayment.config.ApplicationDeserializer");
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
				new JsonDeserializer<>(ApplyPaymentStatusEvents.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ApplyPaymentStatusEvents> midosStubListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, ApplyPaymentStatusEvents> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(midosStubtConsumerFactory());
		return factory;
	}

}
