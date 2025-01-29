package com.onlineParking.Dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineParking.Pojos.SlotAvailability;
import com.onlineParking.Pojos.ParkingSlots;

public interface SlotAvailabilityDao extends JpaRepository<SlotAvailability, Long> {
    List<SlotAvailability> findBySlot(ParkingSlots slot);
    List<SlotAvailability> findByDate(LocalDate date);
}
