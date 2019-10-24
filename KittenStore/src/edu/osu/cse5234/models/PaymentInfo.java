package edu.osu.cse5234.models;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Session Bean implementation class PaymentInfo
 */
@Entity
@Table(name="PAYMENT_INFO")
public class PaymentInfo {	
	
    /**
     * Default constructor. 
     */
    public PaymentInfo() {
    }
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
    private int id;
	
	@Column(name="CARD_NUM")
	private String ccNumber;
	
	@Column(name="EXP_DATE")
	private String expiration;
	
	@Column(name="CVV")
	private String cvvCode;
	
	@Column(name="HOLDER_NAME")
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
