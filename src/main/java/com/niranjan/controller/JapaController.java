package com.niranjan.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niranjan.DTOs.DecrementRequest;
import com.niranjan.DTOs.IncrementRequest;
import com.niranjan.service.JapaService;

@RestController
@RequestMapping("/api/japa")
@CrossOrigin("*")
public class JapaController {
 
	@Autowired
    private JapaService service;
	
    @PostMapping("/increment/{userId}")
    public ResponseEntity<?> increment(
            @PathVariable Long userId,
            @RequestBody IncrementRequest request) {
 
        return ResponseEntity.status(HttpStatus.OK).body(service.incrementBeads(userId, request.getBeadCount()));
    }
    
    @PostMapping("/decrement/{userId}")
    public ResponseEntity<?> decrement(@PathVariable Long userId, @RequestBody DecrementRequest request){
    	return ResponseEntity.status(HttpStatus.OK).body(service.decrementBeads(userId, request.getBeadCount()));
    }
    
    @PostMapping("/reset/{userId}")
    public ResponseEntity<?> reset(@PathVariable Long userId){
    	return ResponseEntity.status(HttpStatus.OK).body(service.resetCount(userId));
    }
 
    @GetMapping("/today/{userId}")
    public ResponseEntity<?> getToday(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTodayStats(userId));
    }
    
    @GetMapping("/lifetime/{userId}")
    public ResponseEntity<?> getLifeTime(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getLifeTimeStats(userId));
    }
    
    @GetMapping("/histroy/{id}")
    public ResponseEntity<?> getHistry(@PathVariable Long id, @RequestParam LocalDate start, @RequestParam LocalDate end){
    	return ResponseEntity.status(HttpStatus.OK).body(service.getHistroy(id, start, end));
    }
    
}
