package com.delivery.models;

import java.sql.Time;

public class Business {
	
	private int id;
	private Time close_time;
	private Time open_time;
	private String phone_number;
	private String url;
	private String name;
	private String mailing_address;
	private int customer_rating;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Time getClose_time() {
		return close_time;
	}
	public void setClose_time(Time close_time) {
		this.close_time = close_time;
	}
	public Time getOpen_time() {
		return open_time;
	}
	public void setOpen_time(Time open_time) {
		this.open_time = open_time;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailing_address() {
		return mailing_address;
	}
	public void setMailing_address(String mailing_address) {
		this.mailing_address = mailing_address;
	}
	public int getCustomer_rating() {
		return customer_rating;
	}
	public void setCustomer_rating(int customer_rating) {
		this.customer_rating = customer_rating;
	}
	
	
}
