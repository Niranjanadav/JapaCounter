package com.niranjan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.niranjan.entities.Japa;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import com.niranjan.entities.User;



public interface JapaRepository extends JpaRepository<Japa, Long> {

	//find Today's record
	Optional<Japa> findByUserAndJapaDate(User user, LocalDate japaDate);
	
	//find record between dates
	List<Japa> findByUserAndJapaDateBetween(User user, LocalDate startDate,LocalDate endDate);
	
	//fetch all records
	List<Japa> findByUserOrderByJapaDateDesc(User user);
	
	//Lifetime total beads
	@Query("SELECT COALESCE(SUM(j.totalBeadCounts), 0) FROM Japa j WHERE j.user = :user")
    Long sumTotalBeadsByUser(User user);
	
}
