package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.models.Car;

public interface CarsRepository extends JpaRepository<Car, Integer> {

}
