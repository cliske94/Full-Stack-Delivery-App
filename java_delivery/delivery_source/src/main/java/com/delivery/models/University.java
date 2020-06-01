package com.delivery.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="university")
public class University {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy="uuid2")
	@Column(name="url")
	private String url;
	private String univ_name;
	private String address;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUniv_name() {
		return univ_name;
	}
	public void setUniv_name(String univ_name) {
		this.univ_name = univ_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
