package com.eon.applypayment.camel;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.eon.applypayment.util.ApplyPaymentStatus;
import com.eon.applypayment.util.Utilz;
import com.eon.applypayment.vo.ApplyPaymentStatusEvent;
import com.eon.applypayment.vo.PostBalanceRequest;
import com.eon.applypayment.vo.PostUtrnRequest;
import com.eon.applypayment.vo.TransactionDetail;
import com.eon.applypayment.vo.UtrnAppliedConfirmationRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class Mapper {

	public ApplyPaymentStatusEvent mapPaymentUtrnRequested(TransactionDetail transactionDetail)
			throws JsonParseException, JsonMappingException, IOException{
		ApplyPaymentStatusEvent applyPaymentStatusEvent = new ApplyPaymentStatusEvent();
		applyPaymentStatusEvent.setEventDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setEventType(ApplyPaymentStatus.PaymentUtrnRequested.toString());
		applyPaymentStatusEvent.setPaygProductId(transactionDetail.getPaygProductId());
		applyPaymentStatusEvent.setReason("Payment");
		applyPaymentStatusEvent.setValue(""+transactionDetail.getValue());
		return applyPaymentStatusEvent;
	}
	
	public ApplyPaymentStatusEvent mapUtrnReceived(PostUtrnRequest postUtrnRequest)
			throws JsonParseException, JsonMappingException, IOException{
		ApplyPaymentStatusEvent applyPaymentStatusEvent = new ApplyPaymentStatusEvent();
		applyPaymentStatusEvent.setEventDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setEventType(ApplyPaymentStatus.UtrnReceived.toString());
		applyPaymentStatusEvent.setPaygProductId(postUtrnRequest.getPaygProductId());
		applyPaymentStatusEvent.setReason("Payment");
		applyPaymentStatusEvent.setUtrn(postUtrnRequest.getUtrn());
		applyPaymentStatusEvent.setValue(""+postUtrnRequest.getValue());
		return applyPaymentStatusEvent;
	}
	
	public ApplyPaymentStatusEvent mapApplyPaymentUtrnRequested(PostUtrnRequest postUtrnRequest)
			throws JsonParseException, JsonMappingException, IOException{
		ApplyPaymentStatusEvent applyPaymentStatusEvent = new ApplyPaymentStatusEvent();
		applyPaymentStatusEvent.setEventDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setEventType(ApplyPaymentStatus.ApplyUtrnRequested.toString());
		applyPaymentStatusEvent.setPaygProductId(postUtrnRequest.getPaygProductId());
		applyPaymentStatusEvent.setReason("Payment");
		applyPaymentStatusEvent.setUtrn(postUtrnRequest.getUtrn());
		return applyPaymentStatusEvent;
	}
	
	
	
	public ApplyPaymentStatusEvent mapUtrnApplied(UtrnAppliedConfirmationRequest utrnAppliedConfirmation)
			throws JsonParseException, JsonMappingException, IOException{
		ApplyPaymentStatusEvent applyPaymentStatusEvent = new ApplyPaymentStatusEvent();
		applyPaymentStatusEvent.setEventDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setEventType(ApplyPaymentStatus.UtrnApplied.toString());
		applyPaymentStatusEvent.setPaygProductId(utrnAppliedConfirmation.getPaygProductId());
		applyPaymentStatusEvent.setReason("Payment");
		applyPaymentStatusEvent.setUtrn(utrnAppliedConfirmation.getUtrn());
		applyPaymentStatusEvent.setValue(""+utrnAppliedConfirmation.getValue());
		return applyPaymentStatusEvent;
	}
	
	public ApplyPaymentStatusEvent mapPostMeterBalance(PostBalanceRequest postBalanceRequest)
			throws JsonParseException, JsonMappingException, IOException{
		ApplyPaymentStatusEvent applyPaymentStatusEvent = new ApplyPaymentStatusEvent();
		applyPaymentStatusEvent.setEventDateTime(Utilz.getCurrentDate());
		applyPaymentStatusEvent.setEventType(ApplyPaymentStatus.MeterBalanceReceived.toString());
		applyPaymentStatusEvent.setPaygProductId(postBalanceRequest.getPaygProductId());
		applyPaymentStatusEvent.setReason("Payment");
		applyPaymentStatusEvent.setMeterBalanceDateTime(postBalanceRequest.getMeterBalanceDate());
		applyPaymentStatusEvent.setMeterBalance(postBalanceRequest.getMeterBalance());
		applyPaymentStatusEvent.setTransactionId(postBalanceRequest.getTransactionId());
		return applyPaymentStatusEvent;
	}
	
//	public mapGetUtrnResponseTo
}