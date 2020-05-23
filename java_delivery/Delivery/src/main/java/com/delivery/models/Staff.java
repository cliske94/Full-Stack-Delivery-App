package com.delivery.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="staff")
public class Staff {
	
	@Id
	private Integer staff_id;
	private String adminYorN;
	private String position;
	private Integer persons_id;
	public String getAdminYorN() {
		return adminYorN;
	}
	public void setAdminYorN(String adminYorN) {
		this.adminYorN = adminYorN;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getPersons_id() {
		return persons_id;
	}
	public void setPersons_id(Integer persons_id) {
		this.persons_id = persons_id;
	}
	public Integer getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(Integer staff_id) {
		this.staff_id = staff_id;
	}
	
}
