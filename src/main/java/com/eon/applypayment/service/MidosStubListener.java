package com.eon.applypayment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.eon.applypayment.util.ApplyPaymentStatus;
import com.eon.applypayment.vo.ApplyPaymentStatusEvent;

@Component
public class MidosStubListener {
	private static final Logger logger = LoggerFactory.getLogger(MidosStubListener.class);

	@Autowired
	private MidosStubService midosStubService;

	@KafkaListener(topics = "${kafka.applyPaymentStatus.topic}", containerFactory = "midosStubListenerFactory", groupId = "group-id")
	public void applyPaymentStatusEventListener(ApplyPaymentStatusEvent applyPaymentStatusEvent) {
		logger.info("MidosStubListener class of applyPaymentStatusEventListener method start :{}",
				applyPaymentStatusEvent.toString());
		applyPaymentUtrnRequested(applyPaymentStatusEvent);
		applyPaymentUtrnApplied(applyPaymentStatusEvent);
		meeterBlanceApplied(applyPaymentStatusEvent);

	}

	private void meeterBlanceApplied(ApplyPaymentStatusEvent applyPaymentStatusEvent) {
		if (ApplyPaymentStatus.UtrnApplied.toString().equals(applyPaymentStatusEvent.getEventType())) {
			midosStubService.meeterBlanceAppliedService(applyPaymentStatusEvent);

		}

	}

	private void applyPaymentUtrnApplied(ApplyPaymentStatusEvent applyPaymentStatusEvent) {
		if (ApplyPaymentStatus.ApplyUtrnRequested.toString().equals(applyPaymentStatusEvent.getEventType())) {
			midosStubService.appPaymentUtrnAppliedService(applyPaymentStatusEvent);

		}

	}

	private void applyPaymentUtrnRequested(ApplyPaymentStatusEvent applyPaymentStatusEvent) {
		if (ApplyPaymentStatus.PaymentUtrnRequested.toString().equals(applyPaymentStatusEvent.getEventType())) {
			midosStubService.appPaymentUtrnRequestedService(applyPaymentStatusEvent);

		}
	}
}
