package com.onlineParking.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineParking.Dao.SlotAvailabilityDao;
import com.onlineParking.Pojos.SlotAvailability;

@Service
@Transactional
public class SlotAvailabilityServiceImpl implements SlotAvailabilityService {
	
	@Autowired
	private SlotAvailabilityDao availabilityDao; 
	
	@Override
	public boolean isSlotAvailable(Long slotId, LocalDate date, LocalDateTime startTime, LocalDateTime  endTime) {
	    List<SlotAvailability> overlappingSlots = availabilityDao.findOverlappingAvailabilities(slotId, date, startTime, endTime);

	    // If any of the overlapping slots are booked, return false (unavailable)
	    for (SlotAvailability slot : overlappingSlots) {
	        if (slot.getIsBooked()) {
	            return false; // Slot is unavailable due to conflict
	        }
	    }

	    // If no conflicts exist, insert a new availability record
	    SlotAvailability newAvailability = new SlotAvailability();
	    newAvailability.setId(slotId);
	    newAvailability.setDate(date);
	    newAvailability.setStartTime(startTime);
	    newAvailability.setEndTime(endTime);
	    newAvailability.setIsBooked(false); // Available
	    newAvailability.setUpdateOn(LocalDateTime.now());

	    availabilityDao.save(newAvailability); // Save to DB

	    return true; // Slot is available
	}
	
	@Override
	public void adjustSlotAvailability(Long slotId, LocalDate date, LocalDateTime startTime, LocalDateTime endTime) {
	    List<SlotAvailability> overlappingSlots = availabilityDao.findOverlappingAvailabilities(slotId, date, startTime, endTime);

	    for (SlotAvailability slot : overlappingSlots) {
	        if (slot.getStartTime().equals(startTime) && slot.getEndTime().equals(endTime)) {
	            // Exact match: delete the availability
	            availabilityDao.delete(slot);
	        } else {
	            // Partial match: split the time slot
	            availabilityDao.delete(slot);

	            if (slot.getStartTime().isBefore(startTime)) {
	                // Create a new availability for the first half
	                SlotAvailability firstHalf = new SlotAvailability();
	                firstHalf.setId(slotId);
	                firstHalf.setDate(date);
	                firstHalf.setStartTime(slot.getStartTime());
	                firstHalf.setEndTime(startTime);
	                firstHalf.setIsBooked(false);
	                availabilityDao.save(firstHalf);
	            }

	            if (slot.getEndTime().isAfter(endTime)) {
	                // Create a new availability for the second half
	                SlotAvailability secondHalf = new SlotAvailability();
	                secondHalf.setId(slotId);
	                secondHalf.setDate(date);
	                secondHalf.setStartTime(endTime);
	                secondHalf.setEndTime(slot.getEndTime());
	                secondHalf.setIsBooked(false);
	                availabilityDao.save(secondHalf);
	            }
	        }
	    }
	}


	
}
