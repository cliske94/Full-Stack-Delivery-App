package com.delivery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.models.Menu;
import com.delivery.repositories.MenusRepository;

@RestController
@RequestMapping("/api")
public class MenusController {
	
	@Autowired
	MenusRepository dao;
	
	@GetMapping("/menus")
	public List<Menu> getMenus()
	{
		List<Menu> foundMenus = dao.findAll();
		return foundMenus;
	}
	
	@GetMapping("/menus/{menu_id}")
	public ResponseEntity<Menu> getMenu(@PathVariable(value="menu_id") Integer id)
	{
		Menu foundMenu = dao.findById(id).orElse(null);
		if (foundMenu == null) return ResponseEntity.notFound().header("Menu", "Nothing found with that id").build();
		return ResponseEntity.ok(foundMenu);
	}
}
