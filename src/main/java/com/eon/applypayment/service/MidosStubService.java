package com.eon.applypayment.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eon.applypayment.util.Utilz;
import com.eon.applypayment.vo.ApplyPaymentStatusEvent;
import com.eon.applypayment.vo.PostBalanceRequest;
import com.eon.applypayment.vo.PostUtrnRequest;

@Service
public class MidosStubService {

	private static final Logger logger = LoggerFactory.getLogger(MidosStubService.class);
	@Autowired
	RestTemplate restTemplate;

	@Value("${applyPaymentServiceUrl}")
	private String applyPaymentServiceUrl;

	public void appPaymentUtrnRequestedService(ApplyPaymentStatusEvent applyPaymentStatusEvent) {
		logger.info("MidosStubService class of appPaymentUtrnRequestedService method start :{}",
				applyPaymentStatusEvent.toString());
		PostUtrnRequest postUtrnRequest = paymentUtrnRequested(applyPaymentStatusEvent);

		StringBuilder url = new StringBuilder();
		url.append(applyPaymentServiceUrl).append("/utrns");

		restTemplate.postForObject(url.toString(), postUtrnRequest, PostUtrnRequest.class);
	}

	private PostUtrnRequest paymentUtrnRequested(ApplyPaymentStatusEvent applyPaymentStatusEvent) {
		PostUtrnRequest postUtrnRequest = new PostUtrnRequest();
		postUtrnRequest.setPaygProductId(applyPaymentStatusEvent.getPaygProductId());
		postUtrnRequest.setTransactionId(applyPaymentStatusEvent.getTransactionId());
		postUtrnRequest.setUtrn(getUtrnId());
		postUtrnRequest.setValue(Float.valueOf(applyPaymentStatusEvent.getValue()));
		return postUtrnRequest;
	}

	public void appPaymentUtrnAppliedService(ApplyPaymentStatusEvent applyPaymentStatusEvent) {
		logger.info("MidosStubService class of appPaymentUtrnAppliedService method start :{}",
				applyPaymentStatusEvent.toString());
		StringBuilder url = new StringBuilder();
		url.append(applyPaymentServiceUrl).append("/utrnapplied");
		com.eon.applypayment.vo.UtrnAppliedConfirmationRequest utrnAppliedConfirmationRequest = createUtrnAppliedConfirmationRequest(
				applyPaymentStatusEvent);
		restTemplate.postForObject(url.toString(), utrnAppliedConfirmationRequest,
				com.eon.applypayment.vo.UtrnAppliedConfirmationRequest.class);
	}

	private com.eon.applypayment.vo.UtrnAppliedConfirmationRequest createUtrnAppliedConfirmationRequest(
			ApplyPaymentStatusEvent applyPaymentStatusEvent) {
		logger.info("MidosStubService class of UtrnAppliedConfirmationRequest method start :{}",
				applyPaymentStatusEvent.toString());
		com.eon.applypayment.vo.UtrnAppliedConfirmationRequest request = new com.eon.applypayment.vo.UtrnAppliedConfirmationRequest();
		request.setPaygProductId(applyPaymentStatusEvent.getPaygProductId());
		request.setTransactionId(applyPaymentStatusEvent.getTransactionId());
		request.setUtrn(applyPaymentStatusEvent.getUtrn());
		request.setValue(Float.valueOf(applyPaymentStatusEvent.getValue()));
		return request;
	}

	public void meeterBlanceAppliedService(ApplyPaymentStatusEvent applyPaymentStatusEvent) {
		logger.info("MidosStubService class of meeterBlanceAppliedService method start :{}",
				applyPaymentStatusEvent.toString());
		StringBuilder url = new StringBuilder();
		url.append(applyPaymentServiceUrl).append("/meterbalance");
		PostBalanceRequest postBalanceRequest = createPostBalanceRequest(applyPaymentStatusEvent);
		restTemplate.postForObject(url.toString(), postBalanceRequest, PostBalanceRequest.class);
	}

	private PostBalanceRequest createPostBalanceRequest(ApplyPaymentStatusEvent applyPaymentStatusEvent) {
		PostBalanceRequest balanceRequest = new PostBalanceRequest();

		balanceRequest.setPaygProductId(applyPaymentStatusEvent.getPaygProductId());
		balanceRequest.setReason(applyPaymentStatusEvent.getReason());
		balanceRequest.setTransactionId(applyPaymentStatusEvent.getTransactionId());
		balanceRequest.setMeterBalance(300);
		balanceRequest.setMeterBalanceDate(Utilz.getCurrentDate());
		return balanceRequest;
	}

	public static String getUtrnId() {
		Random rnd = new Random();
		int number = rnd.nextInt(9999999);
		return String.format("%07d", number);
	}
}
