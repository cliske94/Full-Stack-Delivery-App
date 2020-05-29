package com.delivery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/orders")
	public ResponseEntity<Order> postOrder(@RequestBody Order order) {
		Order createdOrder = dao.save(order);
		return ResponseEntity.ok(createdOrder);
	}
	
	@DeleteMapping("/orders/{order_id}")
	public ResponseEntity<Order> deleteOrder(@PathVariable(value="order_id") Integer id) {
		Order foundOrder = dao.findById(id).orElse(null);
		if (foundOrder == null) return ResponseEntity.notFound().header("Order", "Order not found with that id").build();
		else dao.delete(foundOrder);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/orders/{order_id}")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable(value="order_id") Integer id) {
		Order foundOrder = dao.findById(id).orElse(null);
		if (foundOrder == null) return ResponseEntity.notFound().header("Order", "Nothing found with that id").build();
		else {
			foundOrder.setDelivery_charge(order.getDelivery_charge());
			foundOrder.setDelivery_time_minutes(order.getDelivery_time_minutes());
			foundOrder.setMiles_total(order.getMiles_total());
			foundOrder.setPromotional_factor(order.getPromotional_factor());
			foundOrder.setTimestamp(order.getTimestamp());
			dao.save(foundOrder);
		}
		return ResponseEntity.ok().header("Order", "Properly updated order record").build();
	}
}
