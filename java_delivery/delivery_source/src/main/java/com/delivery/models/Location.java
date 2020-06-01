package com.delivery.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="locations")
public class Location {
	
	@Id
	private int id;
	private String dropoff_description;
	private String name;
	private String location_address;
	private String gps_coordinates;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDropoff_description() {
		return dropoff_description;
	}
	public void setDropoff_description(String dropoff_description) {
		this.dropoff_description = dropoff_description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation_address() {
		return location_address;
	}
	public void setLocation_address(String location_address) {
		this.location_address = location_address;
	}
	public String getGps_coordinates() {
		return gps_coordinates;
	}
	public void setGps_coordinates(String gps_coordinates) {
		this.gps_coordinates = gps_coordinates;
	}
	
	
}
