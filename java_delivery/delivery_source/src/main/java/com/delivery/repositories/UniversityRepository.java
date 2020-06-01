package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.models.University;

public interface UniversityRepository extends JpaRepository<University, Integer> {

}
