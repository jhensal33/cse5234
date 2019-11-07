package com.chase.payment;

public class PaymentProcessor {

	public String ping() {
		return "Bank's Open";
	}

	public String processPayment(CreditCardPayment payment) {

		String confNumber = ((int) Math.random()*1000) + "";

		return confNumber;
	}

}