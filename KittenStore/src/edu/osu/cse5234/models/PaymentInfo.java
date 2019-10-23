package edu.osu.cse5234.models;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class PaymentInfo
 */
@Stateless
@LocalBean
public class PaymentInfo {	
	
    /**
     * Default constructor. 
     */
    public PaymentInfo() {
    }
    
    private int id;
	private String ccNumber;
	private String expiration;
	private String cvvCode;
	private String cardName;

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
