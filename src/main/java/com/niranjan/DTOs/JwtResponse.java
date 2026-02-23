package com.niranjan.DTOs;

public class JwtResponse {

	private String jwtToken;
	private String refreshToken;
	private String username;
	private Long id;

	public JwtResponse() {}
	
	public JwtResponse(String jwtToken, String refreshToken, String username, Long id) {
		this.jwtToken = jwtToken;
		this.refreshToken = refreshToken;
		this.username = username;
		this.id = id;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}