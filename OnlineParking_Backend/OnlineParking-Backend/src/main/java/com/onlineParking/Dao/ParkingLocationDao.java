package com.onlineParking.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineParking.Pojos.ParkingLocation;

public interface ParkingLocationDao extends JpaRepository<ParkingLocation, Long> {
	
}
