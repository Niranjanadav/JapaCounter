package com.niranjan.DTOs;

public class JapaStatsResponse {

	private int todayBeads;
	private int todayRounds;
	private int lifeTimeBeads;
	private int lifeTimeRounds;

	
	public JapaStatsResponse(int todayBeads, int todayRounds, Integer lifeTimeBeads, Integer lifeTimeRounds) {
		this.todayBeads = todayBeads;
		this.todayRounds = todayRounds;
		this.lifeTimeBeads = lifeTimeBeads;
		this.lifeTimeRounds = lifeTimeRounds;
	}

	public int getTodayBeads() {
		return todayBeads;
	}

	public void setTodayBeads(int todayBeads) {
		this.todayBeads = todayBeads;
	}

	public int getTodayRounds() {
		return todayRounds;
	}

	public void setTodayRounds(int todayRounds) {
		this.todayRounds = todayRounds;
	}

	public int getLifeTimeBeads() {
		return lifeTimeBeads;
	}

	public void setLifeTimeBeads(Integer lifeTimeBeads) {
		this.lifeTimeBeads = lifeTimeBeads;
	}

	public int getLifeTimeRounds() {
		return lifeTimeRounds;
	}

	public void setLifeTimeRounds(Integer lifeTimeRounds) {
		this.lifeTimeRounds = lifeTimeRounds;
	}

}
