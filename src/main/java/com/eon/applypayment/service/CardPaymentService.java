package com.eon.applypayment.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eon.applypayment.vo.CardDetails;
import com.eon.applypayment.vo.CardResponse;

@Service
public class CardPaymentService {
	private static final Logger logger = LoggerFactory.getLogger(CardPaymentService.class);
	public CardResponse cardPayment(CardDetails cardDetails) {
		logger.info("CardPaymentService class of cardPayment method start :{}", cardDetails.toString());
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
