package com.eon.applypayment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.eon.applypayment.util.ApplyPaymentStatus;
import com.eon.applypayment.vo.ApplyPaymentStatusEvents;

@Component
public class MidosStubListener {
	private static final Logger logger = LoggerFactory.getLogger(MidosStubListener.class);

	@Autowired
	private MidosStubService midosStubService;

	@KafkaListener(topics = "${kafka.applyPaymentStatus.topic}", containerFactory = "midosStubListenerFactory", groupId = "group-id")
	public void applyPaymentStatusEventListener(ApplyPaymentStatusEvents applyPaymentStatusEvent) {
		logger.info("MidosStubListener class of applyPaymentStatusEventListener method start :{}",
				applyPaymentStatusEvent.toString());
		applyPaymentUtrnRequested(applyPaymentStatusEvent);
		applyPaymentUtrnApplied(applyPaymentStatusEvent);
		meeterBlanceApplied(applyPaymentStatusEvent);

	}

	private void meeterBlanceApplied(ApplyPaymentStatusEvents applyPaymentStatusEvents) {
		logger.info("MidosStubListener class of meeterBlanceApplied method start :{}",
				applyPaymentStatusEvents.toString());
		if (ApplyPaymentStatus.UtrnApplied.toString()
				.equals(applyPaymentStatusEvents.getApplyPaymentStatusEvent().getEventType())) {
			midosStubService.meeterBlanceAppliedService(applyPaymentStatusEvents.getApplyPaymentStatusEvent());

		}

	}

	private void applyPaymentUtrnApplied(ApplyPaymentStatusEvents applyPaymentStatusEvents) {
		logger.info("MidosStubListener class of applyPaymentUtrnApplied method start :{}",
				applyPaymentStatusEvents.toString());
		if (ApplyPaymentStatus.ApplyUtrnRequested.toString()
				.equals(applyPaymentStatusEvents.getApplyPaymentStatusEvent().getEventType())) {
			midosStubService.appPaymentUtrnAppliedService(applyPaymentStatusEvents.getApplyPaymentStatusEvent());

		}

	}

	private void applyPaymentUtrnRequested(ApplyPaymentStatusEvents applyPaymentStatusEvents) {
		logger.info("MidosStubListener class of applyPaymentUtrnRequested method start :{}",
				applyPaymentStatusEvents.toString());
		if (ApplyPaymentStatus.PaymentUtrnRequested.toString()
				.equals(applyPaymentStatusEvents.getApplyPaymentStatusEvent().getEventType())) {
			midosStubService.appPaymentUtrnRequestedService(applyPaymentStatusEvents.getApplyPaymentStatusEvent());

		}
	}
}
