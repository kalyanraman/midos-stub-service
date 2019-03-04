package com.eon.applypayment.vo;

import lombok.Data;

@Data
public class PostBalanceRequest {

	private String paygProductId;
	private String transactionId;
	private String meterBalanceDate;
	private float meterBalance;
	private String reason;

}
