package com.onlineParking.Dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineParking.Pojos.SlotAvailability;
import com.onlineParking.Pojos.ParkingSlots;

public interface SlotAvailabilityDao extends JpaRepository<SlotAvailability, Long> {
    List<SlotAvailability> findBySlot(ParkingSlots slot);
    List<SlotAvailability> findByDate(LocalDate date);
    List<SlotAvailability> findBySlotAndDateAndIsBookedFalse(ParkingSlots slot, LocalDate date);
//    List<SlotAvailability> findByDateAndIsBookedFalse(LocalDate date);
    List<SlotAvailability> findBySlotAndIsBookedFalse(ParkingSlots slot);
//    List<SlotAvailability> findByIsBookedFalse();
}
