package com.delivery.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="rating_history")
public class RatingHistory {
	
	@Id
	private int rating_id;
	private int rating;
	private Timestamp timestamp;
	private String description;
	private int persons_id;
	private int businesses_id;
	private int drivers_id;
	public int getRating_id() {
		return rating_id;
	}
	public void setRating_id(int rating_id) {
		this.rating_id = rating_id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPersons_id() {
		return persons_id;
	}
	public void setPersons_id(int persons_id) {
		this.persons_id = persons_id;
	}
	public int getBusinesses_id() {
		return businesses_id;
	}
	public void setBusinesses_id(int businesses_id) {
		this.businesses_id = businesses_id;
	}
	public int getDrivers_id() {
		return drivers_id;
	}
	public void setDrivers_id(int drivers_id) {
		this.drivers_id = drivers_id;
	}
	
	
}
