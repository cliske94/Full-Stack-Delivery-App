package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.models.Payroll;
 
public interface PayrollsRepository extends JpaRepository<Payroll, Integer>{

}
