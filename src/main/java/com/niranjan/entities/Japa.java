package com.niranjan.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Japa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false)
	private LocalDate japaDate;

	@Column(nullable = false)
	private int currentBeads;
	
	@Column(nullable = false)
	private int currentRounds;
	
	@Column(nullable = false)
	private int totalBeadCounts;

	private LocalDate createdAt;
	private LocalDate updatedAt;

	public Japa() {	}

	


	public Japa(User user, LocalDate japaDate, Integer currentBeads, Integer currentRounds,
			Integer totalBeadCounts, LocalDate createdAt, LocalDate updatedAt) {
		this.user = user;
		this.japaDate = japaDate;
		this.currentBeads = currentBeads;
		this.currentRounds = currentRounds;
		this.totalBeadCounts = totalBeadCounts;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getJapaDate() {
		return japaDate;
	}

	public void setJapaDate(LocalDate japaDate) {
		this.japaDate = japaDate;
	}

	public int getTotalBeadCounts() {
		return totalBeadCounts;
	}

	public void setTotalBeadCounts(Integer totalBeadCounts) {
		this.totalBeadCounts = totalBeadCounts;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getCurrentBeads() {
		return currentBeads;
	}

	public void setCurrentBeads(Integer currentBeads) {
		this.currentBeads = currentBeads;
	}



	public Integer getCurrentRounds() {
		return currentRounds;
	}



	public void setCurrentRounds(Integer currentRounds) {
		this.currentRounds = currentRounds;
	}

}
