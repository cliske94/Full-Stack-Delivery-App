package com.delivery.models;

public class Faculty extends Person {
	
	private int persons_id;
	private String title;
	private String highest_degree;
	private String degree_college;
	public int getPersons_id() {
		return persons_id;
	}
	public void setPersons_id(int persons_id) {
		this.persons_id = persons_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHighest_degree() {
		return highest_degree;
	}
	public void setHighest_degree(String highest_degree) {
		this.highest_degree = highest_degree;
	}
	public String getDegree_college() {
		return degree_college;
	}
	public void setDegree_college(String degree_college) {
		this.degree_college = degree_college;
	}
	
	
}
