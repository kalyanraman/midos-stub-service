package com.eon.applypayment.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
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

}
