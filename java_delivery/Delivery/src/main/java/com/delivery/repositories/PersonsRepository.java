package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.models.Person;

public interface PersonsRepository extends JpaRepository<Person, Integer> {

}
