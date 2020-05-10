package com.delivery.models;

import java.sql.Date;

public class Driver extends Person {
	
	private int license_number;
	private int rating;
	private Date date_hired;
	private int driver_id;
	private int persons_id;
	public int getLicense_number() {
		return license_number;
	}
	public void setLicense_number(int license_number) {
		this.license_number = license_number;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Date getDate_hired() {
		return date_hired;
	}
	public void setDate_hired(Date date_hired) {
		this.date_hired = date_hired;
	}
	public int getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}
	public int getPersons_id() {
		return persons_id;
	}
	public void setPersons_id(int persons_id) {
		this.persons_id = persons_id;
	}
	
	
}