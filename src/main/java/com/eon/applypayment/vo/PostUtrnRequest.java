package com.eon.applypayment.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PostUtrnRequest {

	public PostUtrnRequest() {

	}

	private String paygProductId;
	private String transactionId;
	private String utrn;
	private float value;

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getPaygProductId() {
		return paygProductId;
	}

	public void setPaygProductId(String paygProductId) {
		this.paygProductId = paygProductId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getUtrn() {
		return utrn;
	}

	public void setUtrn(String utrn) {
		this.utrn = utrn;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
