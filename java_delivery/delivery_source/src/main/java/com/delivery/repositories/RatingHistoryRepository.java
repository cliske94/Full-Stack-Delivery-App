package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.models.RatingHistory;

public interface RatingHistoryRepository extends JpaRepository<RatingHistory, Integer> {

}
