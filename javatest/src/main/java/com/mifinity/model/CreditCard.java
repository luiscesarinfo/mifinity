package com.mifinity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CreditCard {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String cardHolder;
	@Column(unique=true)
	private String cardNumber;
	@Column
	private String expiryDate;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
}
