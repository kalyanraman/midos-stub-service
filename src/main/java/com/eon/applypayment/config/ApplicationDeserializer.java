package com.eon.applypayment.config;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eon.applypayment.vo.ApplyPaymentStatusEvents;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApplicationDeserializer implements Deserializer {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationDeserializer.class);

	@Override
	public void close() {
	}

	@Override
	public ApplyPaymentStatusEvents deserialize(String arg0, byte[] arg1) {
		ObjectMapper mapper = new ObjectMapper();
		ApplyPaymentStatusEvents applyPaymentStatusEvents = null;
		try {
			applyPaymentStatusEvents = mapper.readValue(arg1, ApplyPaymentStatusEvents.class);
		} catch (Exception e) {
			logger.error("Error occurred at ApplicationDeserializer of ApplyPaymentStatusEvents method  :{}",
					e.getMessage());
		}
		return applyPaymentStatusEvents;
	}

	@Override
	public void configure(Map configs, boolean isKey) {
		// TODO Auto-generated method stub

	}

}
