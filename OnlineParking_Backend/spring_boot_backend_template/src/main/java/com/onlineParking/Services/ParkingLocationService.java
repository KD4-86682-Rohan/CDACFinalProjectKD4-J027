package com.onlineParking.Services;

import java.util.List;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.ParkingLocationReqDto;
import com.onlineParking.DTO.ParkingLocationRespDto;

public interface ParkingLocationService {
	List<ParkingLocationRespDto> getAllParkingLocations();
	
	ApiResponse addNewParkingLocation(ParkingLocationReqDto dto, Long vendorId);
	
	ApiResponse deleteParkingLocation(Long id, Long vId);
	
	ApiResponse updateParkingLocation(ParkingLocationReqDto dto,Long id, Long vid);
}
