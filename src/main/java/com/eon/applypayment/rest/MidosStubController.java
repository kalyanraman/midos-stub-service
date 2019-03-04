package com.eon.applypayment.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/midos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MidosStubController {
	private static final Logger logger = LoggerFactory.getLogger(CardPaymentStubController.class);

	@GetMapping(path = "/utrns")
	public ResponseEntity<HttpStatus> getUtrn(@RequestParam String paygProductId, @RequestParam String value) {
		logger.info("MidosStubController class of getUtrn method start :{}", paygProductId.toString(), value);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}

	@GetMapping(path = "/utrns", params = "action=apply")
	public ResponseEntity<HttpStatus> getUtrn(@RequestParam String paygProductId, @RequestParam String utrn,
			@RequestParam String value) {
		logger.info("MidosStubController class of getUtrn method start :{}", paygProductId, value, utrn);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

}
