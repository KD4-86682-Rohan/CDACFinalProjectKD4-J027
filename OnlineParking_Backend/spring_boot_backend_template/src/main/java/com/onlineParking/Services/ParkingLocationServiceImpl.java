package com.onlineParking.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.ParkingLocationReqDto;
import com.onlineParking.DTO.ParkingLocationRespDto;
import com.onlineParking.Dao.ParkingLocationDao;
import com.onlineParking.Pojos.ParkingLocation;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ParkingLocationServiceImpl implements ParkingLocationService {
	
	@Autowired
	private ParkingLocationDao parkingLocationDao;
	
	@Autowired
	private ModelMapper modelMapper;

	//get all parking locations
	@Override
	public List<ParkingLocationRespDto> getAllParkingLocations() {
		
		return parkingLocationDao.findAll()
				.stream()
				.map(location-> modelMapper.map(location, ParkingLocationRespDto.class))
				.collect(Collectors.toList());
	}

	//add new parking location
	@Override
	public ApiResponse addNewParkingLocation(ParkingLocationReqDto dto) {
		
		ParkingLocation location = modelMapper.map(dto,ParkingLocation.class);
		ParkingLocation persistentLocation = parkingLocationDao.save(location);
		return new ApiResponse("Added new category with ID="
				+ persistentLocation.getId());
		
	}
	
	
	
}
