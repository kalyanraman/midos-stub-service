package com.eon.applypayment.vo;

import lombok.Data;

@Data
public class GetUtrnResponse {

	private String paygProductId;
	private float value;
	private Long utrn;

}
