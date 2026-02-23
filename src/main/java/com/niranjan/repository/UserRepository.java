package com.niranjan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niranjan.entities.User;



public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByName(String name);
	Optional<User> findByEmail(String email);
	boolean existsByName(String username);
	boolean existsByEmail(String email);
	
}
