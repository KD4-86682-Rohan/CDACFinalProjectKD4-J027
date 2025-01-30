package com.onlineParking.Services;


import java.util.Date;
import java.util.List;

import com.onlineParking.DTO.ParkingSlotRespDto;

public interface SlotAvailabilityService {

	List<ParkingSlotRespDto> findByDate(Long lId, Date date);
	List<ParkingSlotRespDto> findByParkingSlot(Long lId);
}
