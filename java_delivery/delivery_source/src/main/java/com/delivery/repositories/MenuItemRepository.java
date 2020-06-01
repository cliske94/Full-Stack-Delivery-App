package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.models.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

}
