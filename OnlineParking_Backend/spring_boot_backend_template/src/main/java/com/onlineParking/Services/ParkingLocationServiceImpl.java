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
import com.onlineParking.Dao.UserDao;
import com.onlineParking.Pojos.ParkingLocation;
import com.onlineParking.Pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ParkingLocationServiceImpl implements ParkingLocationService {
	
	@Autowired
	private ParkingLocationDao parkingLocationDao;
	@Autowired
	private UserDao userDao;
	
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
	public ApiResponse addNewParkingLocation(ParkingLocationReqDto dto,Long vendorId) {
		User user=userDao.findById(vendorId).orElseThrow(()->new RuntimeException("Invalid Id"));
//		if(user.getRole().equals(Role.Admin)) {
			ParkingLocation location = modelMapper.map(dto,ParkingLocation.class);
			location.setVendor(user);
			ParkingLocation persistentLocation = parkingLocationDao.save(location);
			return new ApiResponse("new parking location added with id="
					+ persistentLocation.getId());
//		}
//		return new ApiResponse("location not added");
		
	}
	
	
	
}
