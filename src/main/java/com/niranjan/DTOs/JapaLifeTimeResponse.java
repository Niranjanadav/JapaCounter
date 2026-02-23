package com.niranjan.DTOs;

public class JapaLifeTimeResponse {

	private Long totalBeads;
	private Long totalRounds;

	public JapaLifeTimeResponse(Long totalBeads, Long totalRounds) {
		this.totalBeads = totalBeads;
		this.totalRounds = totalRounds;
	}

	public Long getTotalBeads() {
		return totalBeads;
	}

	public void setTotalBeads(Long totalBeads) {
		this.totalBeads = totalBeads;
	}

	public Long getTotalRounds() {
		return totalRounds;
	}

	public void setTotalRounds(Long totalRounds) {
		this.totalRounds = totalRounds;
	}

}
