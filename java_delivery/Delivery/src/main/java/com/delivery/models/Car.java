package com.delivery.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="cars")
public class Car {
	
	private int drivers_id;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy="uuid2")
	@Column(name="VIN")
	private String VIN;
	private String insurance_verified_YorN;
	private String color;
	private String make;
	private String model;
	private String plate_number;
	public int getDrivers_id() {
		return drivers_id;
	}
	public void setDrivers_id(int drivers_id) {
		this.drivers_id = drivers_id;
	}
	public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
	}
	public String getInsurance_verified_YorN() {
		return insurance_verified_YorN;
	}
	public void setInsurance_verified_YorN(String insurance_verified_YorN) {
		this.insurance_verified_YorN = insurance_verified_YorN;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPlate_number() {
		return plate_number;
	}
	public void setPlate_number(String plate_number) {
		this.plate_number = plate_number;
	}
	
	
}
