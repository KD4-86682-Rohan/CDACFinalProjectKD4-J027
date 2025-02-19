package com.onlineParking.Services;

import java.util.List;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.DateTimeDto;
import com.onlineParking.DTO.ParkingSlotReqDto;
import com.onlineParking.DTO.ParkingSlotRespDto;

public interface ParkingSlotService {
	List<ParkingSlotRespDto> getAllParkingSlots(Long id);
	ApiResponse addNewParkingSlot(Long lId, ParkingSlotReqDto dto);
	ApiResponse updateParkingSlot(Long id, ParkingSlotReqDto dto);
	ApiResponse deleteParkingSlot(Long id);
	List<ParkingSlotRespDto> getAvailableParkingSlots(Long locationId, DateTimeDto dateTimeDto);
}
