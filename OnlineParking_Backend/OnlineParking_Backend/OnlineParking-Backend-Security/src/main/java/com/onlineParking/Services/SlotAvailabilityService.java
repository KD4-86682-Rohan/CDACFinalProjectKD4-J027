package com.onlineParking.Services;


import java.time.LocalDate;
import java.time.LocalDateTime;

public interface SlotAvailabilityService {

	boolean isSlotAvailable(Long slotId, LocalDate date, LocalDateTime  startTime, LocalDateTime  endTime);
	void adjustSlotAvailability(Long slotId, LocalDate date, LocalDateTime  startTime, LocalDateTime  endTime);

}
