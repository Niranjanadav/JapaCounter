package com.niranjan.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niranjan.DTOs.UserRegisterRequest;
import com.niranjan.DTOs.UserResponse;
import com.niranjan.entities.User;
import com.niranjan.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserResponse registerUser(UserRegisterRequest request) {
		if(userRepository.existsByName(request.getUsername())) {
			throw new RuntimeException("User already exist");
		}
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("User already exist");
		}
		
		User user = new User();
		user.setName(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setCreatedDate(LocalDate.now());
		
		User save = userRepository.save(user);
		
		return new UserResponse(save.getId(), save.getName(), save.getEmail());	
	}
	
	public UserResponse getUserById(Long id) {
		User existUser = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found with id : " + id));
		return new UserResponse(existUser.getId(), existUser.getName(), existUser.getEmail());
	}
	
	public UserResponse getUserByUsername(String username) {
		User existUser = userRepository.findByName(username).orElseThrow(()-> new RuntimeException("User not found with username : " + username));
		return new UserResponse(existUser.getId(), existUser.getName(), existUser.getEmail());
	}
	
}
