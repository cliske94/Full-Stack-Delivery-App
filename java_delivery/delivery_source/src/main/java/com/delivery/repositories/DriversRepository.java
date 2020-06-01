package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.delivery.models.Driver;

public interface DriversRepository extends JpaRepository<Driver, Integer> {

}
