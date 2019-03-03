package com.eon.applypayment.vo;

import java.io.Serializable;

public class TransactionDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String paygProductId;
	private float value;
	// TODO validate date pattern
	private String transactionTimeStamp;

	public String getPaygProductId() {
		return paygProductId;
	}

	public void setPaygProductId(String paygProductId) {
		this.paygProductId = paygProductId;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getTransactionTimeStamp() {
		return transactionTimeStamp;
	}

	public void setTransactionTimeStamp(String transactionTimeStamp) {
		this.transactionTimeStamp = transactionTimeStamp;
	}

	@Override
	public String toString() {
		return "paygProductId - " + paygProductId + ", value - " + value + ", transactionTimeStamp - "
				+ transactionTimeStamp;
	}

}
