package com.eon.applypayment.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eon.applypayment.service.CardPaymentService;
import com.eon.applypayment.vo.CardDetails;
import com.eon.applypayment.vo.CardResponse;

@RestController
@RequestMapping("/cards")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CardPaymentStubController {
	private static final Logger logger = LoggerFactory.getLogger(CardPaymentStubController.class);
	@Autowired
	CardPaymentService cardPaymentService;

	@PostMapping(consumes = "application/json", path = "/cardpayment")
	public CardResponse cardPayment(@Valid @RequestBody CardDetails cardDetails) {
		logger.info("CardPaymentStubController class of cardPayment method start :{}", cardDetails.toString());
		return cardPaymentService.cardPayment(cardDetails);

	}

}
