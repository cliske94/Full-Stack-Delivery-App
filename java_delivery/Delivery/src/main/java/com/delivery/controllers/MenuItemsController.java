package com.delivery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.delivery.models.MenuItem;
import com.delivery.repositories.MenuItemRepository;

@RestController
@RequestMapping("/api")
public class MenuItemsController {

	@Autowired
	MenuItemRepository dao;
	
	@GetMapping("/menu_items")
	public List<MenuItem> getItems()
	{
		List<MenuItem> foundItems = dao.findAll();
		return foundItems;
	}
	
	@GetMapping("/menu_items/{item_code}")
	public ResponseEntity<MenuItem> getItem(@PathVariable(value="item_code") Integer id)
	{
		MenuItem foundItem = dao.findById(id).orElse(null);
		if (foundItem == null) return ResponseEntity.notFound().header("MenuItem", "Nothing found with that code").build();
		return ResponseEntity.ok(foundItem);
	}
}
