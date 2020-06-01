package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.models.Student;

public interface StudentsRepository extends JpaRepository<Student, Integer> {

}
