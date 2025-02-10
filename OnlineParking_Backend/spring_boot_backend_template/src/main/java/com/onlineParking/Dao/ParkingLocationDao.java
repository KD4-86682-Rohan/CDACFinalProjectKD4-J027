package com.onlineParking.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onlineParking.Pojos.ParkingLocation;

public interface ParkingLocationDao extends JpaRepository<ParkingLocation, Long> {
<<<<<<< HEAD
//	List<ParkingLocation> findDistinctCities();
=======
	@Query("SELECT DISTINCT p.city FROM ParkingLocation p")
	List<String> findDistinctCities();

>>>>>>> caab4336dab92430f4672398c9d31ff8c3c15003
}
