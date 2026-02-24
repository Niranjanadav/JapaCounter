package com.niranjan.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niranjan.entities.RefreshToken;
import com.niranjan.entities.User;
import com.niranjan.repository.RefreshTokenRepository;
import com.niranjan.repository.UserRepository;

@Service
public class RefreshTokenService {
	
	private long refreshTokenValidity = 10*60*60*1000;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	public RefreshToken createRefreshToken(String userName){
		User user = userRepository.findByName(userName).get();
		RefreshToken refreshToken = user.getRefreshToken();
		if(refreshToken==null) {
			RefreshToken refreshToken1 = new RefreshToken();
			refreshToken1.setRefresToken(UUID.randomUUID().toString());
			refreshToken1.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
			refreshToken1.setUser(user);
			refreshToken = refreshToken1;
		}
		else {
			refreshToken.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
		}
		
		user.setRefreshToken(refreshToken);
		refreshTokenRepository.save(refreshToken);
		
	    return refreshToken;
	}
	
	public RefreshToken verifyRefreshToken(String refreshToken) {
		RefreshToken refreshTokenOb = refreshTokenRepository.findByRefresToken(refreshToken).orElseThrow(()-> new RuntimeException("Token does not exist..!!!"));
		
		if(refreshTokenOb.getExpiry().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(refreshTokenOb);
			throw new RuntimeException("Refresh token expired..!!!");
		}

        return refreshTokenOb;
	}
	
}
