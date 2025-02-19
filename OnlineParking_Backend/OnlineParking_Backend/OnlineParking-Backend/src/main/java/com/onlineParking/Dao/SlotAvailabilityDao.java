package com.onlineParking.Dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineParking.Pojos.SlotAvailability;
import com.onlineParking.Pojos.User;
import com.onlineParking.Pojos.ParkingSlots;

public interface SlotAvailabilityDao extends JpaRepository<SlotAvailability, Long> {
    List<SlotAvailability> findBySlot(ParkingSlots slot);
    List<SlotAvailability> findByDate(LocalDate date);
    List<SlotAvailability> findBySlotAndDateAndIsBookedFalse(ParkingSlots slot, LocalDate date);
//    List<SlotAvailability> findByDateAndIsBookedFalse(LocalDate date);
    List<SlotAvailability> findBySlotAndIsBookedFalse(ParkingSlots slot);
//    List<SlotAvailability> findByIsBookedFalse();
    List<SlotAvailability> findBySlotAndDate(ParkingSlots slot, LocalDate date);
	Optional<SlotAvailability> findBySlotAndDateAndStartTime(ParkingSlots slot, LocalDate localDate, LocalTime localTime);
}
