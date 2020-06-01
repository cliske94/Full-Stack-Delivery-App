package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.models.Order;

public interface OrdersRepository extends JpaRepository<Order, Integer> {

}
