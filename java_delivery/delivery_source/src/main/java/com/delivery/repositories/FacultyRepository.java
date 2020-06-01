package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.delivery.models.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

}
