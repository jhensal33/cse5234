package com.chase.payment;

public class PaymentProcessor {

	public String ping() {
		return "Bank's Open";
	}

	public String processPayment(CreditCardPayment payment) {

		String confNumber = "123";

		return confNumber;
	}

	public PaymentProcessor getPaymentProcessorPort() {
		// TODO Auto-generated method stub
		return null;
	}

}