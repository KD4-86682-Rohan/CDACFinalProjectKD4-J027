package com.onlineParking.Dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onlineParking.Pojos.ParkingSlots;
import com.onlineParking.Pojos.SlotAvailability;

@Repository
public interface SlotAvailabilityDao extends JpaRepository<SlotAvailability, Long> {
    
    @Query("SELECT sa FROM SlotAvailability sa WHERE sa.slot.id = :slotId AND sa.date = :date " +
           "AND ((sa.startTime <= :endTime AND sa.endTime >= :startTime))")
    List<SlotAvailability> findOverlappingAvailabilities(@Param("slotId") Long slotId,
                                                         @Param("date") LocalDate date,
                                                         @Param("startTime") LocalDateTime startTime,
                                                         @Param("endTime") LocalDateTime endTime);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM SlotAvailability sa WHERE sa.slot.id = :slotId AND sa.date = :date " +
           "AND sa.startTime >= :startTime AND sa.endTime <= :endTime")
    void deleteExactAvailability(@Param("slotId") Long slotId, 
                                 @Param("date") LocalDate date,
                                 @Param("startTime") LocalDateTime startTime, 
                                 @Param("endTime") LocalDateTime endTime);
    
    List<SlotAvailability> findBySlotAndDate(ParkingSlots slot, LocalDate date);
}
