package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
