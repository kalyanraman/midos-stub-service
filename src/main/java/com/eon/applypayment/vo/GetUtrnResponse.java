package com.eon.applypayment.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GetUtrnResponse {

	public GetUtrnResponse() {
	}

	public GetUtrnResponse(String paygProductId, String value) {
		this.paygProductId = paygProductId;
		this.value = Float.parseFloat(value);
		this.utrn = 3423842420948L;
	}

	public GetUtrnResponse(GetUtrnRequest getUtrnRequest) {
		this.paygProductId = getUtrnRequest.getPaygProductId();
		this.value = getUtrnRequest.getValue();
		this.utrn = 3423842420948L;
	}

	private String paygProductId;
	private float value;
	private Long utrn;

	public Long getUtrn() {
		return utrn;
	}

	public void setUtrn(Long utrn) {
		this.utrn = utrn;
	}

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
