package com.eon.applypayment.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/midos")
public class MidosStubController {

	@GetMapping(path = "/utrns")
	public ResponseEntity<HttpStatus> getUtrn(@RequestParam String paygProductId, @RequestParam String value) {
		System.out.println("inside getutrn - " + paygProductId + "...." + value);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}

	@GetMapping(path = "/utrns", params = "action=apply")
	public ResponseEntity<HttpStatus> getUtrn(@RequestParam String paygProductId, @RequestParam String utrn,
			@RequestParam String value) {
		System.out.println("appy utrn - " + paygProductId + " - " + utrn + " - " + value);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

}
