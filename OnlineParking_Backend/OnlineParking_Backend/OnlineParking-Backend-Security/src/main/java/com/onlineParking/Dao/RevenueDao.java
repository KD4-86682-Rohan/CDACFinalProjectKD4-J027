package com.onlineParking.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineParking.Pojos.ParkingLocation;
import com.onlineParking.Pojos.Revenue;
import com.onlineParking.Pojos.User;

@Repository
public interface RevenueDao extends JpaRepository<Revenue, Long> {
    
    Revenue findByLocation(ParkingLocation location);
    Revenue findByLocationAndUser(ParkingLocation location, User vendor);
}
