package com.onlineParking.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineParking.Pojos.ParkingSlots;
import com.onlineParking.Pojos.ParkingLocation;
import com.onlineParking.Pojos.SlotStatus;
import com.onlineParking.Pojos.SlotType;

public interface ParkingSlotDao extends JpaRepository<ParkingSlots, Long> {
    List<ParkingSlots> findByLocation(ParkingLocation location);
    List<ParkingSlots> findByStatus(SlotStatus status);
    List<ParkingSlots> findBySlotType(SlotType slotType);
}
