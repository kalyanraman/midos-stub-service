package com.eon.applypayment.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GetUtrnRequest {

	private String paygProductId;
	private float value;

	
}
