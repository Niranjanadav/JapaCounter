package com.niranjan.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niranjan.DTOs.JapaLifeTimeResponse;
import com.niranjan.DTOs.JapaStatsResponse;
import com.niranjan.entities.Japa;
import com.niranjan.entities.User;
import com.niranjan.repository.JapaRepository;
import com.niranjan.repository.UserRepository;

@Service
public class JapaService {

	@Autowired
	private JapaRepository japaRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public JapaStatsResponse incrementBeads(Long userId, int incrementBeads) {
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
 
        LocalDate today = LocalDate.now();
        
        Japa japa = japaRepository.findByUserAndJapaDate(user, today)
                                  .orElseGet(()->new Japa(user,today,0,0,0,today,today));
        
        japa.setTotalBeadCounts(japa.getTotalBeadCounts()+incrementBeads); 
        
        int newCount = japa.getCurrentBeads()+incrementBeads;
       
        japa.setCurrentRounds(japa.getCurrentRounds()+(newCount/108));
        japa.setCurrentBeads(newCount%108);
        japa.setUpdatedAt(today);
   
        Japa save = japaRepository.save(japa);
        
        return new JapaStatsResponse(save.getCurrentBeads(), save.getCurrentRounds(), save.getTotalBeadCounts(), save.getTotalBeadCounts()/108);
	}
	
	
	public JapaStatsResponse getTodayStats(Long id) {
		User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
 
        LocalDate today = LocalDate.now();
        
        Japa japa = japaRepository.findByUserAndJapaDate(user, today).orElseThrow(()->new RuntimeException("Japa entry is not found"));
        
        return new JapaStatsResponse(japa.getCurrentBeads(), japa.getCurrentRounds(), japa.getTotalBeadCounts(), japa.getTotalBeadCounts()/108);
	}
	
	public JapaLifeTimeResponse getLifeTimeStats(Long id) {
		User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found with id : " + id));
		Long totalBeads = japaRepository.sumTotalBeadsByUser(user);
		Long totalRounds = totalBeads / 108;
		
		return new JapaLifeTimeResponse(totalBeads, totalRounds);
	}
	
	public List<Japa> getHistroy(Long id, LocalDate start, LocalDate end){
		User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found with id : " + id));
		return japaRepository.findByUserAndJapaDateBetween(user, start, end);
	}
	
}
