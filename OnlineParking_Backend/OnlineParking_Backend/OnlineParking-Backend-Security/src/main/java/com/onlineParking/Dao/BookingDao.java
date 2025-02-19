package com.onlineParking.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onlineParking.Pojos.Bookings;
import com.onlineParking.Pojos.ParkingLocation;
import com.onlineParking.Pojos.User;

public interface BookingDao extends JpaRepository<Bookings, Long> {
    List<Bookings> findByUser(User user);
    List<Bookings> findByVendor(User vender);
    
    @Query("SELECT b FROM Bookings b WHERE b.vendor = :vendor AND b.slot.location = :location")
    List<Bookings> findByVendorAndLocation(@Param("vendor") User vendor, @Param("location") ParkingLocation location);

    
}
