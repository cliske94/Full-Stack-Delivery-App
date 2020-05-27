package com.delivery.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

	@GetMapping("/user-info")
	public ResponseEntity<Map<Object, Object>> currentUser(@AuthenticationPrincipal UserDetails userDetails) {
		Map<Object, Object> model = new HashMap<>();
		model.put("username", userDetails.getUsername());
		model.put("roles", userDetails.getAuthorities());
		return ResponseEntity.ok(model);
	}
}
