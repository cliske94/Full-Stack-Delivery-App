package com.delivery.models;

public class Payment {
	
	private double amount;
	private String payment_type;
	private int card_number;
	private int exp_date;
	private int cvv;
	private int zipcode;
	private int Persons_id;
	private int payment_id;
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public int getCard_number() {
		return card_number;
	}
	public void setCard_number(int card_number) {
		this.card_number = card_number;
	}
	public int getExp_date() {
		return exp_date;
	}
	public void setExp_date(int exp_date) {
		this.exp_date = exp_date;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public int getPersons_id() {
		return Persons_id;
	}
	public void setPersons_id(int persons_id) {
		Persons_id = persons_id;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	
	
}
