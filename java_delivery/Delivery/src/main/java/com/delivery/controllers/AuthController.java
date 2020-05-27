package com.delivery.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliver.api.auth.JwtTokenProvider;
import com.delivery.models.AuthenticationRequest;
import com.delivery.repositories.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	UserRepository users;
	
	@PostMapping("/signin")
	public ResponseEntity<Map<Object, Object>> signin(@RequestBody AuthenticationRequest data) {
		String username = data.getUsername();
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
		String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username).getRoles());
		Map<Object, Object> model = new HashMap<>();
		model.put("username", username);
		model.put("token", token);
		return ResponseEntity.ok(model);
	}
}
