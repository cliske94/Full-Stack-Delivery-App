package com.delivery.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

	@Id
	private Integer persons_id;
	private Integer student_id;
	private int grad_year;
	private String major;
	private String program_type;

	public Integer getPersons_id() {
		return persons_id;
	}

	public void setPersons_id(Integer persons_id) {
		this.persons_id = persons_id;
	}

	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}

	public int getGrad_year() {
		return grad_year;
	}

	public void setGrad_year(int grad_year) {
		this.grad_year = grad_year;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getProgram_type() {
		return program_type;
	}

	public void setProgram_type(String program_type) {
		this.program_type = program_type;
	}

}
