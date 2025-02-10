package com.onlineParking.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineParking.Pojos.Revenue;
import com.onlineParking.Pojos.ParkingLocation;

public interface RevenueDao extends JpaRepository<Revenue, Long> {
    List<Revenue> findByLocation(ParkingLocation location);
    Optional<Revenue> findByLocationIdAndUserId(Long locationId, Long userId);
}
