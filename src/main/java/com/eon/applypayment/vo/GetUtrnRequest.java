package com.eon.applypayment.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GetUtrnRequest {

	public GetUtrnRequest() {

	}

	private String paygProductId;
	private float value;

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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
