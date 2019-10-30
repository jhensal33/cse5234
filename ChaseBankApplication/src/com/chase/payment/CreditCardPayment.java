package com.chase.payment;

import java.io.Serializable;

public class CreditCardPayment implements Serializable {

    private int id;
	
	private String ccNumber;
	
	private String expiration;
	
	private String cvvCode;
	
	private String cardName;
	
	private String emailAddress;
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCcNumber() {
		return ccNumber;
	}
	
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public String getCvvCode() {
		return cvvCode;
	}
	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	
}
