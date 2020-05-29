package com.delivery.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menus")
public class Menu {
	
	@Id
	private int menu_id;
	private int businesses_id;
	private String name;
	
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public int getBusinesses_id() {
		return businesses_id;
	}
	public void setBusinesses_id(int businesses_id) {
		this.businesses_id = businesses_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
