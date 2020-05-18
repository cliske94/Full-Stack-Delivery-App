package com.delivery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.models.Order;
import com.delivery.repositories.OrdersRepository;

@RestController
@RequestMapping("/api")
public class OrdersController {

	@Autowired
	OrdersRepository dao;
	
	@GetMapping("/orders")
	public List<Order> getOrders()
	{
		List<Order> foundOrders = dao.findAll();
		return foundOrders;
	}
	
	@GetMapping("/orders/{order_id}")
	public ResponseEntity<Order> getOrder(@PathVariable(value="order_id") Integer id)
	{
		Order foundOrder = dao.findById(id).orElse(null);
		if (foundOrder == null) return ResponseEntity.notFound().header("Order", "Nothing found with that id").build();
		return ResponseEntity.ok(foundOrder);
	}
}
