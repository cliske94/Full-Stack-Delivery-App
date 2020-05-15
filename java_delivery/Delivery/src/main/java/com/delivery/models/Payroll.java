package com.delivery.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="payroll")
public class Payroll {
	
	@Id
	private int transaction_id;
	private double gross_pay;
	private float hours_worked;
	private double taxes_taken;
	private Timestamp timestamp;
	private double YTD_pay;
	private int drivers_id;
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public double getGross_pay() {
		return gross_pay;
	}
	public void setGross_pay(double gross_pay) {
		this.gross_pay = gross_pay;
	}
	public float getHours_worked() {
		return hours_worked;
	}
	public void setHours_worked(float hours_worked) {
		this.hours_worked = hours_worked;
	}
	public double getTaxes_taken() {
		return taxes_taken;
	}
	public void setTaxes_taken(double taxes_taken) {
		this.taxes_taken = taxes_taken;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public double getYTD_pay() {
		return YTD_pay;
	}
	public void setYTD_pay(double yTD_pay) {
		YTD_pay = yTD_pay;
	}
	public int getDrivers_id() {
		return drivers_id;
	}
	public void setDrivers_id(int drivers_id) {
		this.drivers_id = drivers_id;
	}
	
	
}
