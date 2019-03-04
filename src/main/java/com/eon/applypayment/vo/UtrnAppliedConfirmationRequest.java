package com.eon.applypayment.vo;

import lombok.Data;

@Data
public class UtrnAppliedConfirmationRequest {

	private String paygProductId;
	private String transactionId;
	private String utrn;
	private float value;

}
