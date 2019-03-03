package com.eon.applypayment.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eon.applypayment.service.CardPaymentService;
import com.eon.applypayment.vo.CardDetails;
import com.eon.applypayment.vo.CardResponse;

@RestController
@RequestMapping("/cards")
public class CardPaymentStubController {
	@Autowired
	CardPaymentService cardPaymentService;

	@PostMapping(consumes = "application/json", path = "/cardpayment")
	public CardResponse cardPayment(@Valid @RequestBody CardDetails cardDetails) {
		return cardPaymentService.cardPayment(cardDetails);

		
	}

}
