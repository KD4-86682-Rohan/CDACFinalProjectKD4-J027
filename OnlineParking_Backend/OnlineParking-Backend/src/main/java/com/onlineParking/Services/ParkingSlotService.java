package com.onlineParking.Services;

import java.util.List;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.ParkingSlotReqDto;
import com.onlineParking.DTO.ParkingSlotRespDto;
import com.onlineParking.Pojos.SlotStatus;
import com.onlineParking.Pojos.SlotType;

public interface ParkingSlotService {
	List<ParkingSlotRespDto> getAllParkingSlots(Long id);
	ApiResponse addNewParkingSlot(Long lId, ParkingSlotReqDto dto);
	ApiResponse updateParkingSlot(Long id, ParkingSlotReqDto dto);
	ApiResponse deleteParkingSlot(Long id);
	List<ParkingSlotRespDto> getAllBySlotStatus(Long lId, SlotStatus status);
	List<ParkingSlotRespDto> getAllBySlotType(Long lId, SlotType type);
}
