package com.niranjan.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.niranjan.DTOs.UserRegisterRequest;
import com.niranjan.DTOs.UserResponse;
import com.niranjan.customExceptions.UserAlreadyExistException;
import com.niranjan.customExceptions.UserNotFoundException;
import com.niranjan.entities.User;
import com.niranjan.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserResponse registerUser(UserRegisterRequest request) {
		if(userRepository.existsByName(request.getUsername())) {
			throw new UserAlreadyExistException("User already exist with name : " + request.getUsername());
		}
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new UserAlreadyExistException("User already exist with email : " + request.getEmail());
		}
		
		User user = new User();
		user.setName(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassWord(passwordEncoder.encode(request.getPassword()));
		user.setCreatedDate(LocalDate.now());
		
		User save = userRepository.save(user);
		
		return new UserResponse(save.getId(), save.getName(), save.getEmail());	
	}
	
	public UserResponse getUserById(Long id) {
		User existUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found with id : " + id));
		return new UserResponse(existUser.getId(), existUser.getName(), existUser.getEmail());
	}
	
	public UserResponse getUserByUsername(String username) {
		User existUser = userRepository.findByName(username).orElseThrow(()-> new UserNotFoundException("User not found with username : " + username));
		return new UserResponse(existUser.getId(), existUser.getName(), existUser.getEmail());
	}
	
}
