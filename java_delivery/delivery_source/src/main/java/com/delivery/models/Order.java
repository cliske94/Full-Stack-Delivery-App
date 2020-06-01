package com.delivery.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="orders")
public class Order {
	@Id
	private int order_id;
	private int drivers_driver_id;
	private int businesses_id;
	private Timestamp timestamp;
	private Float miles_total;
	private Float promotional_factor;
	private int delivery_charge;
	private Integer delivery_time_minutes;
	private int locations_id;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getDrivers_driver_id() {
		return drivers_driver_id;
	}
	public void setDrivers_driver_id(int drivers_driver_id) {
		this.drivers_driver_id = drivers_driver_id;
	}
	public int getBusinesses_id() {
		return businesses_id;
	}
	public void setBusinesses_id(int businesses_id) {
		this.businesses_id = businesses_id;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public Float getMiles_total() {
		return miles_total;
	}
	public void setMiles_total(Float miles_total) {
		this.miles_total = miles_total;
	}
	public Float getPromotional_factor() {
		return promotional_factor;
	}
	public void setPromotional_factor(Float promotional_factor) {
		this.promotional_factor = promotional_factor;
	}
	public int getDelivery_charge() {
		return delivery_charge;
	}
	public void setDelivery_charge(int delivery_charge) {
		this.delivery_charge = delivery_charge;
	}
	public Integer getDelivery_time_minutes() {
		return delivery_time_minutes;
	}
	public void setDelivery_time_minutes(Integer delivery_time_minutes) {
		this.delivery_time_minutes = delivery_time_minutes;
	}
	public int getLocations_id() {
		return locations_id;
	}
	public void setLocations_id(int locations_id) {
		this.locations_id = locations_id;
	}
	
	
}
