package com.delivery.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="staff")
public class Staff {
	
	private char adminYorN;
	private String position;
	@Id
	private int persons_id;
	public char getAdminYorN() {
		return adminYorN;
	}
	public void setAdminYorN(char adminYorN) {
		this.adminYorN = adminYorN;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getPersons_id() {
		return persons_id;
	}
	public void setPersons_id(int persons_id) {
		this.persons_id = persons_id;
	}
	
	
}
