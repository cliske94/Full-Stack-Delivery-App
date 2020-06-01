package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.models.Payment;

public interface PaymentsRepository extends JpaRepository<Payment, Integer>{

}
