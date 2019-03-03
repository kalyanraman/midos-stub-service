package com.eon.applypayment.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eon.applypayment.vo.ApplyPaymentStatusEvent;
import com.eon.applypayment.vo.PostBalanceRequest;
import com.eon.applypayment.vo.PostUtrnRequest;

@Service
public class MidosStubService {
	@Autowired
	RestTemplate restTemplate;

	@Value("${applyPaymentServiceUrl}")
	private String applyPaymentServiceUrl;

	public void appPaymentUtrnRequestedService(ApplyPaymentStatusEvent applyPaymentStatusEvent) {
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
		StringBuilder url = new StringBuilder();
		url.append(applyPaymentServiceUrl).append("/utrnapplied");
		com.eon.applypayment.vo.UtrnAppliedConfirmationRequest utrnAppliedConfirmationRequest = createUtrnAppliedConfirmationRequest(
				applyPaymentStatusEvent);
		restTemplate.postForObject(url.toString(), utrnAppliedConfirmationRequest,
				com.eon.applypayment.vo.UtrnAppliedConfirmationRequest.class);
	}

	private com.eon.applypayment.vo.UtrnAppliedConfirmationRequest createUtrnAppliedConfirmationRequest(
			ApplyPaymentStatusEvent applyPaymentStatusEvent) {
		com.eon.applypayment.vo.UtrnAppliedConfirmationRequest request = new com.eon.applypayment.vo.UtrnAppliedConfirmationRequest();
		request.setPaygProductId(applyPaymentStatusEvent.getPaygProductId());
		request.setTransactionId(getUtrnId());
		request.setUtrn(applyPaymentStatusEvent.getUtrn());
		request.setValue(Float.valueOf(10.0f));
		return request;
	}

	public static String getUtrnId() {
		Random rnd = new Random();
		int number = rnd.nextInt(99999);
		return String.format("%05d", number);
	}

	public void meeterBlanceAppliedService(ApplyPaymentStatusEvent applyPaymentStatusEvent) {
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

		balanceRequest.setMeterBalance(applyPaymentStatusEvent.getMeterBalance());
		balanceRequest.setMeterBalanceDate(applyPaymentStatusEvent.getMeterBalanceDateTime());
		return balanceRequest;
	}

}
