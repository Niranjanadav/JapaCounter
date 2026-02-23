package com.niranjan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niranjan.DTOs.UserRegisterRequest;
import com.niranjan.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService service;
	
    @PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest request){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.registerUser(request));
	}
    
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
    	return ResponseEntity.status(HttpStatus.OK).body(service.getUserById(id));
    }
    
    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username){
    	return ResponseEntity.status(HttpStatus.OK).body(service.getUserByUsername(username));
    }
	
}

