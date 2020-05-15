package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.models.Location;

public interface LocationsRepository extends JpaRepository<Location, Integer> {

}
