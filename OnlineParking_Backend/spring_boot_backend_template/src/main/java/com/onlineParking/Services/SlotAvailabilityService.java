package com.onlineParking.Services;


import java.time.LocalDate;
import java.util.List;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.ParkingSlotRespDto;
import com.onlineParking.DTO.SlotAvailabilityReqDto;

public interface SlotAvailabilityService {

	List<ParkingSlotRespDto> findByDate(Long lId, LocalDate date);
	List<ParkingSlotRespDto> findByParkingSlot(Long lId);
	ApiResponse addNewSlotAvailability(Long sId, SlotAvailabilityReqDto dto);
}
