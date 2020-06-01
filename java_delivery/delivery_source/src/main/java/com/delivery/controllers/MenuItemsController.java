package com.delivery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/menu_items")
	public ResponseEntity<MenuItem> postItem(@RequestBody MenuItem menu_item) {
		MenuItem createdItem = dao.save(menu_item);
		return ResponseEntity.ok(createdItem);
	}
	
	@DeleteMapping("/menu_items/{item_code}")
	public ResponseEntity<MenuItem> deleteItem(@PathVariable(value="item_code") Integer id) {
		MenuItem foundItem = dao.findById(id).orElse(null);
		if(foundItem == null) return ResponseEntity.notFound().header("Menu Item", "Item not found with that id").build();
		else dao.delete(foundItem);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/menu_items/{item_code}")
	public ResponseEntity<MenuItem> updateItem(@RequestBody MenuItem item, @PathVariable(value="item_code") Integer id) {
		MenuItem foundItem = dao.findById(id).orElse(null);
		if (foundItem == null) return ResponseEntity.notFound().header("Menu Item", "Nothing found with that id").build();
		else {
			foundItem.setCost(item.getCost());
			foundItem.setDescription(item.getDescription());
			foundItem.setName(item.getName());
			dao.save(foundItem);
		}
		return ResponseEntity.ok().header("Menu Item", "Properly updated item record").build();
	}
}
