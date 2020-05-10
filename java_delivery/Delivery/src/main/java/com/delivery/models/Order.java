package com.delivery.models;

import java.sql.Timestamp;

public class Order {
	
	private int order_id;
	private int drivers_driver_id;
	private int businesses_id;
	private Timestamp timestamp;
	private float miles_total;
	private float promotional_factor;
	private int delivery_charge;
	private int delivery_time_minutes;
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
	public float getMiles_total() {
		return miles_total;
	}
	public void setMiles_total(float miles_total) {
		this.miles_total = miles_total;
	}
	public float getPromotional_factor() {
		return promotional_factor;
	}
	public void setPromotional_factor(float promotional_factor) {
		this.promotional_factor = promotional_factor;
	}
	public int getDelivery_charge() {
		return delivery_charge;
	}
	public void setDelivery_charge(int delivery_charge) {
		this.delivery_charge = delivery_charge;
	}
	public int getDelivery_time_minutes() {
		return delivery_time_minutes;
	}
	public void setDelivery_time_minutes(int delivery_time_minutes) {
		this.delivery_time_minutes = delivery_time_minutes;
	}
	public int getLocations_id() {
		return locations_id;
	}
	public void setLocations_id(int locations_id) {
		this.locations_id = locations_id;
	}
	
	
}
