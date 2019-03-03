package com.eon.applypayment.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ApplyPaymentStatusEvent {

	/**
	 * 
	 */
	
	private String eventType;
	private String paygProductId;
	private String eventDateTime;
	private String transactionId;
	private String reason;
	private String value;
	private String utrn;
	private float meterBalance;
	private String meterBalanceDateTime;
	
	
	public float getMeterBalance() {
		return meterBalance;
	}
	public void setMeterBalance(float meterBalance) {
		this.meterBalance = meterBalance;
	}
	public String getMeterBalanceDateTime() {
		return meterBalanceDateTime;
	}
	public void setMeterBalanceDateTime(String meterBalanceDateTime) {
		this.meterBalanceDateTime = meterBalanceDateTime;
	}
	public String getUtrn() {
		return utrn;
	}
	public void setUtrn(String utrn) {
		this.utrn = utrn;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getPaygProductId() {
		return paygProductId;
	}
	public void setPaygProductId(String paygProductId) {
		this.paygProductId = paygProductId;
	}
	public String getEventDateTime() {
		return eventDateTime;
	}
	public void setEventDateTime(String eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}



