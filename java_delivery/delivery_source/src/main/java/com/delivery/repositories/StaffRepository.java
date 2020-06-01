package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.models.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> { 

}
