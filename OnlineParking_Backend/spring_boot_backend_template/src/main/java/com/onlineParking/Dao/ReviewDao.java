package com.onlineParking.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineParking.Pojos.Reviews;
import com.onlineParking.Pojos.User;
import com.onlineParking.Pojos.ParkingLocation;

public interface ReviewDao extends JpaRepository<Reviews, Long> {
    List<Reviews> findByUser(User user);
    List<Reviews> findByLocation(ParkingLocation location);
}
