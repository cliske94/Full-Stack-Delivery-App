package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.models.Menu;

public interface MenusRepository extends JpaRepository<Menu, Integer>
{

}
