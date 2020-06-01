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
	
	@PostMapping("/menus")
	public ResponseEntity<Menu> postMenu(@RequestBody Menu menu) {
		Menu createdMenu = dao.save(menu);
		return ResponseEntity.ok(createdMenu);
	}
	
	@DeleteMapping("/menus/{menu_id}")
	public ResponseEntity<Menu> deleteMenu(@PathVariable(value="menu_id") Integer id) {
		Menu foundMenu = dao.findById(id).orElse(null);
		if (foundMenu == null) return ResponseEntity.notFound().header("Menu", "Nothing found with that id").build();
		else dao.delete(foundMenu);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/menus/{menu_id}")
	public ResponseEntity<Menu> updateMenu(@RequestBody Menu menu, @PathVariable(value="menu_id") Integer id) {
		Menu foundMenu = dao.findById(id).orElse(null);
		if (foundMenu == null) return ResponseEntity.notFound().header("Menu", "Nothing found with that id").build();
		else {
			foundMenu.setName(menu.getName());
			dao.save(foundMenu);
		}
		return ResponseEntity.ok().header("Menu", "Properly updated menu record").build();
	}
}
