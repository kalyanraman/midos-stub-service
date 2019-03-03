package com.eon.applypayment.service;

import java.util.Random;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.eon.applypayment.vo.CardDetails;
import com.eon.applypayment.vo.CardResponse;

@Service
public class CardPaymentService {

	public CardResponse cardPayment(@Valid CardDetails cardDetails) {
		CardResponse cardResponse = new CardResponse();
		cardResponse.setAuthorizationCode(generateRandomWord(5));
		return cardResponse;
	}

	static String generateRandomWord(int wordLength) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder(wordLength);
		for (int i = 0; i < wordLength; i++) {
			char tmp = (char) ('a' + r.nextInt('z' - 'a'));
			sb.append(tmp);
		}
		return sb.toString();
	}

}
