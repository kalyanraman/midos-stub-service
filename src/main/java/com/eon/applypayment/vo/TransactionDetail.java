package com.eon.applypayment.vo;

import lombok.Data;

@Data
public class TransactionDetail {

	private String paygProductId;
	private float value;
	// TODO validate date pattern
	private String transactionTimeStamp;

}
